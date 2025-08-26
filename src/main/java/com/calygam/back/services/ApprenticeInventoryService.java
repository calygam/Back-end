package com.calygam.back.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.EmporiumStockDTO;
import com.calygam.back.dtos.InventoryPetsDTO;
import com.calygam.back.dtos.StockPetDTO;
import com.calygam.back.dtos.StockSkinsDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.PetStatusEnergyEnum;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.mappers.InventoryMappers;
import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.models.ControlApprenticePetEntity;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.StockPetOutfitProjection;
import com.calygam.back.projections.StockPetProjection;
import com.calygam.back.repositories.ApprenticeInventoryRepository;
import com.calygam.back.repositories.ControlApprenticePetRepository;
import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.PetRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@Service
public class ApprenticeInventoryService {
	
	@Autowired
	private ApprenticeInventoryRepository apprenticeInventoryRepository;
	
	@Autowired
	private ControlApprenticePetRepository controlApprenticePetRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private PetOutfitRepository petOutfitRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private InventoryMappers inventoryMappers;
	
	public Boolean checkItemIsInInventory(Long userId,ItemCatalogInventoryEnum catalog) {
		Boolean apprenticeInventoryExists = apprenticeInventoryRepository.existsByApprentice_UserIdAndApprenticeInventoryTag(userId, catalog);
		if(apprenticeInventoryExists) {
			return true;
		}
		return false;
	}

	public ApiSucessHandler<String> userIsEqquipedPet(Long userId,ItemCatalogInventoryEnum itemCatalogType,Long itemTryEquipId){
		PetEntity petEntity = petRepository.findById(itemTryEquipId).orElseThrow(()->new SoftNotFoundException("Pet não permitiu o acesso ao guarda-roupa :("));
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()->new SoftNotFoundException("Controle de usuário não encontrado"));
		ApprenticeInventoryEntity verifyExistInInventorySecondWave = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId,itemCatalogType,petEntity.getPetId())
				.orElseThrow(()-> new SoftNotFoundException("Ops! parece que você nao tem esse pet"));
		ApprenticeInventoryEntity apprenticeInventoryItemEquipped = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(userId,ItemCatalogInventoryEnum.PET).orElse(null);
		
		if(apprenticeInventoryItemEquipped!=null) {
			apprenticeInventoryItemEquipped.setApprenticeInventoryEquipped(false);
			apprenticeInventoryRepository.save(apprenticeInventoryItemEquipped);
			
			if(apprenticeInventoryItemEquipped.getApprenticeInventoryId().equals(verifyExistInInventorySecondWave.getApprenticeInventoryId())){
				return new ApiSucessHandler<String>(true, "Pet desequipado com sucesso!", null);
			}
		}
	
			verifyExistInInventorySecondWave.setApprenticeInventoryEquipped(true);
			ControlApprenticePetEntity foundControl = controlApprenticePetRepository.findByApprenticeUserIdAndPetId(userId, petEntity.getPetId()).orElse(null);
			ControlApprenticePetEntity controlApprenticePetEntity = new ControlApprenticePetEntity();
			ControlApprenticePetEntity controlEntity = foundControl==null? controlApprenticePetEntity:foundControl;
			if(foundControl==null) {
				controlApprenticePetEntity.setPet(petEntity);
				controlApprenticePetEntity.setApprenticePetEnergy(petEntity.getPetDefaultEnergy());
				controlApprenticePetEntity.setApprenticePetEnergyState(PetStatusEnergyEnum.EXHAUSTED);
				controlApprenticePetEntity.setApprenticePetEquippedSkin(true);
				
				controlApprenticePetEntity.setApprentice(userEntity);
				controlApprenticePetRepository.save(controlApprenticePetEntity);
			}
			AtomicInteger counter = new AtomicInteger(0);
			ApprenticeInventoryEntity apprenticeInventoryItemSkinEquipped = apprenticeInventoryRepository.targetExistsByEquippedTrueAndPetId(petEntity.getPetId(),userId).orElseThrow(()->new SoftNotFoundException("Pet não permitiu acesso ao guarda roupa de novo"));
			PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(apprenticeInventoryItemSkinEquipped.getApprenticeInventoryItemId()) .orElseThrow(()-> new SoftNotFoundException("Acesso ao guarda-roupa porém skin recusa"));
			String[] petIdentifiedPack = petOutfitEntity.getPetOutfitPackageSkin().split("_");
			petEntity.getOutfits().forEach(outfit->{
				if(counter.get()>=2)return;
				String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
				if(outfitParts[0].length()>0 && outfitParts[0].contains(petIdentifiedPack[0])) {
				ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

				apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(controlEntity.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
				apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
				 counter.incrementAndGet();
				}
			});
			return new ApiSucessHandler<String>(true, "Pet Equipado com sucesso!", null);
	
	}
	
	
	
	
	//caio<- EQUIPANDO Uma skin
	public ApiSucessHandler<String> userIsEqquipedSkin(Long userId,ItemCatalogInventoryEnum itemCatalogType,Long itemTryEquipId){
		PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(itemTryEquipId) .orElseThrow(()-> new SoftNotFoundException("Acesso ao guarda-roupa porém skin recusa"));
		PetEntity petEntity = petRepository.findById(petOutfitEntity.getPet().getPetId()).orElseThrow(()->new SoftNotFoundException("Pet não permitiu o acesso ao guarda-roupa :("));

		ApprenticeInventoryEntity verifyExistInInventorySecondWave = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId,itemCatalogType,petOutfitEntity.getPetOutfitId())
				.orElseThrow(()-> new SoftNotFoundException("Ops! parece que você nao tem essa SKIN"));
		ApprenticeInventoryEntity apprenticeInventoryItemEquipped = apprenticeInventoryRepository.targetExistsByEquippedTrueAndPetId(petEntity.getPetId(),userId).orElse(null);
		ControlApprenticePetEntity foundControl = controlApprenticePetRepository.findByApprenticeUserIdAndPetId(userId, petEntity.getPetId()).orElseThrow(()->new SoftNotFoundException("controle não setado - SKIN"));

		ControlApprenticePetEntity controlEntity = foundControl;
		if(apprenticeInventoryItemEquipped!=null) {
			apprenticeInventoryItemEquipped.setApprenticeInventoryEquipped(false);
			apprenticeInventoryRepository.save(apprenticeInventoryItemEquipped);
			
			if(apprenticeInventoryItemEquipped.getApprenticeInventoryId().equals(verifyExistInInventorySecondWave.getApprenticeInventoryId())){
				AtomicInteger counter = new AtomicInteger(0);
				petEntity.getOutfits().forEach(outfit->{
					
					if(counter.get()>=2)return;
					String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
					if(outfitParts[0].length()>0 && outfitParts[0].contains("DEFAULT")) {
					ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

					apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(controlEntity.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
					apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
					 counter.incrementAndGet();
					}
				});
				
				return new ApiSucessHandler<String>(true, "SKIN desequipada com sucesso!", null);
			}
		}
	
			verifyExistInInventorySecondWave.setApprenticeInventoryEquipped(true);
		
			AtomicInteger counter = new AtomicInteger(0);
			//ApprenticeInventoryEntity apprenticeInventoryItemSkinEquipped = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(userId, ItemCatalogInventoryEnum.SKIN).orElseThrow(()->new SoftNotFoundException("Pet não permitiu acesso ao guarda roupa de novo"));

			String[] petIdentifiedPack = petOutfitEntity.getPetOutfitPackageSkin().split("_");
			petEntity.getOutfits().forEach(outfit->{
				if(counter.get()>=2)return;
				String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
				if(outfitParts[0].length()>0 && outfitParts[0].contains(petIdentifiedPack[0])) {
				ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

				apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(controlEntity.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
				apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
				 counter.incrementAndGet();
				}
			});
			return new ApiSucessHandler<String>(true, "Traje Equipado com sucesso!", null);
	
	}
	
	public List<InventoryPetsDTO> getInvPets(Long userId){
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Ops não te encontramos"));
		return apprenticeInventoryRepository.getInventoryPetsOfOneUser(userId)
				.stream()
				.map(inv -> inventoryMappers.toInvPetsDTO(inv,userEntity.getUserFood())).collect(Collectors.toList());
		
		
	}
	
	public InventoryPetsDTO getPetEquipped(Long userId){
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Ops não te encontramos"));
		if(	apprenticeInventoryRepository.getInventoryPetEquipped(userId)!=null) {
	
		InventoryPetsDTO petEquipped = inventoryMappers.toInvPetsDTO(apprenticeInventoryRepository.getInventoryPetEquipped(userId),userEntity.getUserFood()) ;
	
		
		return petEquipped;
		}else {
			return null;
		}
		
	}
	
	public List<InventoryPetsDTO> getPetEquippedSkins(Long userId){
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Ops não te encontramos"));
		return apprenticeInventoryRepository.getInventoryPetEquippedSkins(userId)
				.stream()
				.map(inv -> inventoryMappers.toInvPetsDTO(inv,userEntity.getUserFood())).collect(Collectors.toList());
		
	}

}
