package com.calygam.back.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.CreatePetDTO;
import com.calygam.back.dtos.NewOutfitDTO;
import com.calygam.back.dtos.PetDTO;
import com.calygam.back.exceptions.OneValueIsNullException;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.mappers.PetMapper;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.PetRepository;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired 
	private PetOutfitRepository petOutfitRepository;
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	@Autowired
	private PetMapper petMapper;
	
	public String createANewPet(CreatePetDTO petDTO) throws IOException {
		PetEntity verifyExists = petRepository.findByPetName(petDTO.getPetName()).orElse(null);
		String message = "tá com algum erro provavelmente";
		if(verifyExists==null) {
		PetEntity petEntity = new PetEntity();
		
		//caio<- CRIANDO  O CÉREBRO SEM SABER QUEM É O CORPO
	
		
		if(petDTO.getPetName()==null || petDTO.getPetName().isEmpty()) {
			throw new OneValueIsNullException("O nome do pet não pode ser vazio");
		}
		if(petDTO.getPetBoostMoney()==null || petDTO.getPetBoostMoney()<0) {
			throw new OneValueIsNullException("O boost de money do pet não pode ser vazio ou tem que ser maior que 0");
		}
		if(petDTO.getPetBoostXp()==null || petDTO.getPetBoostXp()<0) {
			throw new OneValueIsNullException("O boost de XP do pet não pode ser vazio ou tem que ser maior que 0");
		}
		if(petDTO.getPetBoostFood()==null || petDTO.getPetBoostFood()<0) {
			throw new OneValueIsNullException("O boost de FOOD do pet não pode ser vazio ou tem que ser maior que 0");
		}
	
		petEntity.setPetName(petDTO.getPetName());
		petEntity.setPetBoostMoney(petDTO.getPetBoostMoney());
		petEntity.setPetBoostXp(petDTO.getPetBoostXp());
		petEntity.setPetBoostFood(petDTO.getPetBoostFood());
		petEntity.setPetMinEnergy(petDTO.getPetMinEnergy());
		petEntity.setPetDefaultEnergy(petDTO.getPetDefaultEnergy());
		petEntity.setPetMaxEnergy(petDTO.getPetMaxEnergy());
		petEntity.setPetStatusEnergy(petDTO.getPetStatusEnergy());
		
		petRepository.save(petEntity);
		
		//caio<- CRIANDO O CORPO SABENDO QUEM É A CABEÇA
		
		PetOutfitEntity petOutfitEntity = new PetOutfitEntity();
		
		petOutfitEntity.setPetOutfitName(petDTO.getPetOutfitName());
		petOutfitEntity.setPetOutfitPlusMoney(petDTO.getPetPlusMoney());
		petOutfitEntity.setPetOutfitPlusXp(petDTO.getPetPlusXp());
		petOutfitEntity.setPetOutfitPlusFood(petDTO.getPetPlusFood());
		petOutfitEntity.setPetOutfitSkinMode(petDTO.getPetOutfitMode());
		petOutfitEntity.setPetOutfitPackageSkin(petDTO.getPetOutfitPackageSkin());
		petOutfitEntity.setPet(petEntity);
		makeUploadAndDownloadArchive.saveArchive(petDTO.getPetOutfitImage(),petOutfitEntity,petOutfitRepository);

		message="Provavelmente deu sucesso falta averiguar";
		}
		return message;
	}
	
	
		public String PetNewSkinUnlocked(Long petId,NewOutfitDTO newOutfitDTO) throws IOException {
			PetEntity petEntity = petRepository.findById(petId).orElseThrow(()-> new SoftNotFoundException("Pet não encontrado!"));
			String message = "tá com algum erro provavelmente";
		PetOutfitEntity petOutfitEntity = new PetOutfitEntity();
		
		petOutfitEntity.setPetOutfitName(newOutfitDTO.getPetOutfitName());
		petOutfitEntity.setPetOutfitPlusMoney(newOutfitDTO.getPetPlusMoney());
		petOutfitEntity.setPetOutfitPlusXp(newOutfitDTO.getPetPlusXp());
		petOutfitEntity.setPetOutfitPlusFood(newOutfitDTO.getPetPlusFood());
		petOutfitEntity.setPetOutfitSkinMode(newOutfitDTO.getPetOutfitMode());
		petOutfitEntity.setPetOutfitPackageSkin(newOutfitDTO.getPetOutfitPackageSkin());
		petOutfitEntity.setPet(petEntity);
		makeUploadAndDownloadArchive.saveArchive(newOutfitDTO.getPetOutfitImage(),petOutfitEntity,petOutfitRepository);
		
		message="Provavelmente deu sucesso falta averiguar";
		return message;
	}
	//CAIO<- Juntando cabeça corpo, TUDO :0	
	public List<PetDTO> searchAllPetWithSkins(){
	return petRepository.searchAllPets().stream().map(pet->petMapper.toPetDTO(pet)).collect(Collectors.toList());	
	}
}
