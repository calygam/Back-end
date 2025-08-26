package com.calygam.back.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.CreatePetDTO;
import com.calygam.back.dtos.NewOutfitDTO;
import com.calygam.back.dtos.PetDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.PetStatusEnergyEnum;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.OneValueIsNullException;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.mappers.PetMapper;
import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.models.ControlApprenticePetEntity;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.ApprenticeInventoryRepository;
import com.calygam.back.repositories.ControlApprenticePetRepository;
import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.PetRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private ApprenticeInventoryRepository apprenticeInventoryRepository;
	
	@Autowired 
	private PetOutfitRepository petOutfitRepository;
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	@Autowired
	private PetMapper petMapper;
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired ControlApprenticePetRepository controlRepository;
	
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
	
	
	public ApiSucessHandler<String> feedOnePet(Long userId, Long petId,Boolean feedMax){
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado!"));
		PetEntity petEntity = petRepository.findById(petId).orElseThrow(()-> new SoftNotFoundException("Pet não quer ser alimentado!"));
		
		ControlApprenticePetEntity controlApprenticePetEntity  = controlRepository.findByApprenticeUserIdAndPetId(userId, petId).orElseThrow(()-> new SoftNotFoundException("Controle não encontrado!"));
		Long maxEnergy = petEntity.getPetMaxEnergy();
	    Long baseFraction = (maxEnergy != null) ? Math.round((double) maxEnergy / 10) : 0L;

		if(feedMax!=null && feedMax) {
			System.out.println("ENTROU NO FEED!");
			if(userEntity.getUserFood()>=baseFraction && controlApprenticePetEntity.getApprenticePetEnergy() < petEntity.getPetMaxEnergy()) {
				System.out.println("O USUÁRIO PODE PAGAR AO  MENOS UMA PARCELA!");
		
				for(Long multiplier =1L; multiplier<=10L ;multiplier++) {
					
					if(baseFraction<=userEntity.getUserFood() && controlApprenticePetEntity.getApprenticePetEnergy() < petEntity.getPetMaxEnergy()) {
						Long plusEnergy = controlApprenticePetEntity.getApprenticePetEnergy()+baseFraction;
						if(plusEnergy> petEntity.getPetMaxEnergy()) {
							Long  remainingAmountNecessary = petEntity.getPetMaxEnergy() - controlApprenticePetEntity.getApprenticePetEnergy();
							controlApprenticePetEntity.setApprenticePetEnergy(petEntity.getPetMaxEnergy());
							userEntity.setUserFood(userEntity.getUserFood() - remainingAmountNecessary);

						}else {
						controlApprenticePetEntity.setApprenticePetEnergy(controlApprenticePetEntity.getApprenticePetEnergy()+baseFraction);
			
						userEntity.setUserFood(userEntity.getUserFood()-baseFraction);
						}
						if(controlApprenticePetEntity.getApprenticePetEnergy()>= petEntity.getPetMinEnergy()) {
							controlApprenticePetEntity.setApprenticePetEnergyState(PetStatusEnergyEnum.HAPPY);
							AtomicInteger counter = new AtomicInteger(0);
							ApprenticeInventoryEntity apprenticeInventoryItemSkinEquipped = apprenticeInventoryRepository.targetExistsByEquippedTrueAndPetId(petEntity.getPetId(),userId).orElseThrow(()->new SoftNotFoundException("Pet não permitiu acesso ao guarda roupa de novo"));
							PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(apprenticeInventoryItemSkinEquipped.getApprenticeInventoryItemId()) .orElseThrow(()-> new SoftNotFoundException("Acesso ao guarda-roupa porém skin recusa"));
							String[] petIdentifiedPack = petOutfitEntity.getPetOutfitPackageSkin().split("_");
							petEntity.getOutfits().forEach(outfit->{
								if(counter.get()>=2)return;
								String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
								if(outfitParts[0].length()>0 && outfitParts[0].contains(petIdentifiedPack[0])) {
								ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

								apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(controlApprenticePetEntity.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
								apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
								 counter.incrementAndGet();
								}
							});
							
						}
				
					}else {
						break;
					}
				}
			}else {
				throw new ExcededMaxDelimiter("Ou não temos comida ou já estou cheio mestre");
			}
			controlRepository.save(controlApprenticePetEntity);
			return new ApiSucessHandler<String>(true, "Pet ganhou energia!", null);
		}else if(userEntity.getUserFood()>=baseFraction && controlApprenticePetEntity.getApprenticePetEnergy() < petEntity.getPetMaxEnergy())  {
			if(baseFraction<=userEntity.getUserFood()) {
				Long plusEnergy = controlApprenticePetEntity.getApprenticePetEnergy()+baseFraction;
				if(plusEnergy> petEntity.getPetMaxEnergy()) {
					Long  remainingAmountNecessary = petEntity.getPetMaxEnergy() - controlApprenticePetEntity.getApprenticePetEnergy();
					controlApprenticePetEntity.setApprenticePetEnergy(petEntity.getPetMaxEnergy());
					userEntity.setUserFood(userEntity.getUserFood() - remainingAmountNecessary);
				}else {
				controlApprenticePetEntity.setApprenticePetEnergy(controlApprenticePetEntity.getApprenticePetEnergy()+baseFraction);
				userEntity.setUserFood(userEntity.getUserFood()-baseFraction);
				}
				if(controlApprenticePetEntity.getApprenticePetEnergy()>= petEntity.getPetMinEnergy()) {
					controlApprenticePetEntity.setApprenticePetEnergyState(PetStatusEnergyEnum.HAPPY);
					AtomicInteger counter = new AtomicInteger(0);
					ApprenticeInventoryEntity apprenticeInventoryItemSkinEquipped = apprenticeInventoryRepository.targetExistsByEquippedTrueAndPetId(petEntity.getPetId(),userId).orElseThrow(()->new SoftNotFoundException("Pet não permitiu acesso ao guarda roupa de novo"));
					PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(apprenticeInventoryItemSkinEquipped.getApprenticeInventoryItemId()) .orElseThrow(()-> new SoftNotFoundException("Acesso ao guarda-roupa porém skin recusa"));
					String[] petIdentifiedPack = petOutfitEntity.getPetOutfitPackageSkin().split("_");
					petEntity.getOutfits().forEach(outfit->{
						if(counter.get()>=2)return;
						String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
						if(outfitParts[0].length()>0 && outfitParts[0].contains(petIdentifiedPack[0])) {
						ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

						apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(controlApprenticePetEntity.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
						apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
						 counter.incrementAndGet();
						}
					});

				}
			}
			controlRepository.save(controlApprenticePetEntity);
			return new ApiSucessHandler<String>(true, "Pet ganhou energia!", null);
		}else {
			throw new ExcededMaxDelimiter("O seu pet não pode ser alimentado!");
		}
		
	}

}
