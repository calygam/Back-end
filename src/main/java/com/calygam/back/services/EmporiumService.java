package com.calygam.back.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.EmporiumItemDTO;
import com.calygam.back.dtos.EmporiumStockDTO;
import com.calygam.back.dtos.StockPetDTO;
import com.calygam.back.dtos.StockSkinsDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.PetStatusEnergyEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.MissingMinRolesException;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.mappers.CalygamEmporiumMappers;
import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.models.CalygamEmporiumEntity;
import com.calygam.back.models.ControlApprenticePetEntity;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.StockPetOutfitProjection;
import com.calygam.back.projections.StockPetProjection;
import com.calygam.back.repositories.ApprenticeInventoryRepository;
import com.calygam.back.repositories.ControlApprenticePetRepository;
import com.calygam.back.repositories.EmporiumRepository;
import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.PetRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;
import com.calygam.back.utils.CheckRequiredBeforePurchaseUtil;

import jakarta.transaction.Transactional;

@Service
public class EmporiumService {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PetOutfitRepository petOutfitRepository;
	
	@Autowired
	private ApprenticeInventoryRepository apprenticeInventoryRepository;
	
	@Autowired
	private CheckRequiredBeforePurchaseUtil checkRequiredBeforePurchaseUtil;
	
	@Autowired
	private ControlApprenticePetRepository controlApprenticePetRepository;
	
	@Autowired
	private CalygamEmporiumMappers calygamEmporiumMappers;
	
	@Autowired
	private EmporiumRepository emporiumRepository;
	public ApiSucessHandler<String> AddNewItemToEmporium( EmporiumItemDTO emporiumItemDto) {
		String message = "provavel erro ao tentar adicionar o item";
		
		CalygamEmporiumEntity calygamEmporiumEntity = new CalygamEmporiumEntity();
		if(emporiumItemDto.getEmporiumItemCatalogType()==ItemCatalogInventoryEnum.PET) {
			
			PetEntity petEntity = petRepository.findById(emporiumItemDto.getEmporiumItemId()).orElseThrow(()-> new SoftNotFoundException("Esse pet não foi encontrado!"));
			CalygamEmporiumEntity foundExistentPET = emporiumRepository.findByEmporiumItemIdAndEmporiumItemCatalogType(emporiumItemDto.getEmporiumItemId(),emporiumItemDto.getEmporiumItemCatalogType()).orElse(null);
			if(foundExistentPET==null) {
			calygamEmporiumEntity.setEmporiumItemId(petEntity.getPetId());
			calygamEmporiumEntity.setEmporiumItemCatalogType(emporiumItemDto.getEmporiumItemCatalogType());
			calygamEmporiumEntity.setEmporiumItemGoldCost(emporiumItemDto.getEmporiumItemGoldCost());
			calygamEmporiumEntity.setEmporiumItemRankRequired(emporiumItemDto.getEmporiumItemRankRequired());
			calygamEmporiumEntity.setEmporiumItemSelling(emporiumItemDto.getEmporiumItemSelling());
			calygamEmporiumEntity.setEmporiumItemQtd(emporiumItemDto.getEmporiumItemQtd());
			emporiumRepository.save(calygamEmporiumEntity);
			return new ApiSucessHandler<>(true, "TEMOS UM NOVO PET A VENDA", null);
			}else {
				throw new ExcededMaxDelimiter("este pet já está a venda");
			}
		}else if(emporiumItemDto.getEmporiumItemCatalogType()==ItemCatalogInventoryEnum.SKIN) {
			PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(emporiumItemDto.getEmporiumItemId()).orElseThrow(()-> new SoftNotFoundException("Essa skin não foi encontrada!"));
			CalygamEmporiumEntity foundExistentSKIN = emporiumRepository.findByEmporiumItemIdAndEmporiumItemCatalogType(emporiumItemDto.getEmporiumItemId(),emporiumItemDto.getEmporiumItemCatalogType()).orElse(null);
			if(foundExistentSKIN==null) {
			calygamEmporiumEntity.setEmporiumItemId(petOutfitEntity.getPetOutfitId());
			calygamEmporiumEntity.setEmporiumItemCatalogType(emporiumItemDto.getEmporiumItemCatalogType());
			calygamEmporiumEntity.setEmporiumItemGoldCost(emporiumItemDto.getEmporiumItemGoldCost());
			calygamEmporiumEntity.setEmporiumItemRankRequired(emporiumItemDto.getEmporiumItemRankRequired());
			calygamEmporiumEntity.setEmporiumItemSelling(emporiumItemDto.getEmporiumItemSelling());
			calygamEmporiumEntity.setEmporiumItemQtd(emporiumItemDto.getEmporiumItemQtd());
			emporiumRepository.save(calygamEmporiumEntity);
			return new ApiSucessHandler<>(true, "TEMOS UM NOVO PET A VENDA", null);
			}else {
				throw new ExcededMaxDelimiter("essa SKIN já está a venda");
			}
		}else if(emporiumItemDto.getEmporiumItemCatalogType()==ItemCatalogInventoryEnum.THEME) {
			return new ApiSucessHandler<>(true, "Etapa de tema a ser explorada!", null);
		}else {
			throw new SoftNotFoundException("Não encontramos a opção");
		}	
	}
	
	@Transactional
	public ApiSucessHandler<String> apprenticePurchaseOneItem(Long userId, Long emporiumItemId,ItemCatalogInventoryEnum emporiumItemCatalogType) {
		CalygamEmporiumEntity foundExistentItemInEmporium = emporiumRepository.findByEmporiumItemIdAndEmporiumItemCatalogType(emporiumItemId,emporiumItemCatalogType).orElseThrow(()-> new SoftNotFoundException("Este item não está no nosso catalogo!"));
		Long OptionGetterNumber = checkRequiredBeforePurchaseUtil.CheckValidationStock(foundExistentItemInEmporium);
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado!"));
		if(userEntity.getUserRole()==UserRoleEnum.COORDENADOR || userEntity.getUserRole()==UserRoleEnum.INSTRUTOR) {
			throw new MissingMinRolesException("Ops!, este serviço é para ALUNOS");
		}
		if(OptionGetterNumber==1) { 
			throw new ExcededMaxDelimiter("Ops! esse item esgotou");
			}
		ApprenticeInventoryEntity verifyExistInInventory = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, foundExistentItemInEmporium.getEmporiumItemCatalogType(),foundExistentItemInEmporium.getEmporiumItemId()).orElse(null);
		if(verifyExistInInventory==null) {
			if(emporiumItemCatalogType==ItemCatalogInventoryEnum.PET) {
	

							ApprenticeInventoryEntity apprenticeInventoryEntity = new ApprenticeInventoryEntity();

							Boolean userIsQualifiedForPurchase = checkRequiredBeforePurchaseUtil.checkRequiredBeforePurchase(userEntity, foundExistentItemInEmporium);
							if(!userIsQualifiedForPurchase) {
								throw new MissingMinRolesException("O usuário não possúi o rank ou coins necessários");
							}
							if(userIsQualifiedForPurchase) {
								userEntity.setUserMoney(userEntity.getUserMoney() - foundExistentItemInEmporium.getEmporiumItemGoldCost());
								usersRepository.save(userEntity);
								if(OptionGetterNumber==2) {
								foundExistentItemInEmporium.setEmporiumItemQtd(foundExistentItemInEmporium.getEmporiumItemQtd()-1);
								emporiumRepository.save(foundExistentItemInEmporium);
								}
								
								
								apprenticeInventoryEntity.setApprenticeInventoryItemId(emporiumItemId);
								apprenticeInventoryEntity.setApprenticeInventoryTag(foundExistentItemInEmporium.getEmporiumItemCatalogType());
								apprenticeInventoryEntity.setApprenticeInventoryEquipped(false);
								apprenticeInventoryEntity.setApprenticeInventoryCreatedAt(LocalDateTime.now());
								apprenticeInventoryEntity.setApprentice(userEntity);
								apprenticeInventoryRepository.save(apprenticeInventoryEntity);

								ApprenticeInventoryEntity verifyExistInInventorySecondWave = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId,foundExistentItemInEmporium.getEmporiumItemCatalogType(),foundExistentItemInEmporium.getEmporiumItemId())
										.orElseThrow(()-> new SoftNotFoundException("Falha na conexão de um Pet com a skin dele - Not found"));
							
							
									
									PetEntity petEntity = petRepository.findById(verifyExistInInventorySecondWave.getApprenticeInventoryItemId()).orElseThrow(()->new SoftNotFoundException("Pet não permitiu o acesso ao guarda-roupa :("));
									AtomicInteger counter = new AtomicInteger(0);
									petEntity.getOutfits().forEach(outfit->{
										if(counter.get()>=2)return;
										ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = new ApprenticeInventoryEntity();
										
										apprenticeOutfitsInventoryEntity.setApprenticeInventoryItemId(outfit.getPetOutfitId());
										apprenticeOutfitsInventoryEntity.setApprenticeInventoryTag(ItemCatalogInventoryEnum.SKIN);
										apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(outfit.getPetOutfitPackageSkin().contains("EXHAUSTED")?true:false);
										apprenticeOutfitsInventoryEntity.setApprenticeInventoryCreatedAt(LocalDateTime.now());
										apprenticeOutfitsInventoryEntity.setApprentice(userEntity);
										apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
										 counter.incrementAndGet();
									});
									
									ControlApprenticePetEntity foundControl = controlApprenticePetRepository.findByApprenticeUserIdAndPetId(userId, petEntity.getPetId()).orElse(null);
									ControlApprenticePetEntity controlApprenticePetEntity = new ControlApprenticePetEntity();

									if(foundControl==null) {
										controlApprenticePetEntity.setPet(petEntity);
										controlApprenticePetEntity.setApprenticePetEnergy(petEntity.getPetDefaultEnergy());
										controlApprenticePetEntity.setApprenticePetEnergyState(PetStatusEnergyEnum.EXHAUSTED);
										
										
										controlApprenticePetEntity.setApprentice(userEntity);
										controlApprenticePetRepository.save(controlApprenticePetEntity);
									}
								
							
	
							
						}
							return new ApiSucessHandler<>(true, "Compra realizada com sucesso!", "PET comprado");
		}
					
					else if(emporiumItemCatalogType==ItemCatalogInventoryEnum.SKIN) {
						System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
						System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
						ApprenticeInventoryEntity apprenticeInventoryEntity = new ApprenticeInventoryEntity();
						Boolean userIsQualifiedForPurchase = checkRequiredBeforePurchaseUtil.checkRequiredBeforePurchase(userEntity, foundExistentItemInEmporium);
						if(!userIsQualifiedForPurchase) {
							throw new MissingMinRolesException("O usuário não possúi o rank ou coins necessários");
						}
						if(userIsQualifiedForPurchase) {
							userEntity.setUserMoney(userEntity.getUserMoney() - foundExistentItemInEmporium.getEmporiumItemGoldCost());
							usersRepository.save(userEntity);
							if(OptionGetterNumber==2) {
							foundExistentItemInEmporium.setEmporiumItemQtd(foundExistentItemInEmporium.getEmporiumItemQtd()-1);
							emporiumRepository.save(foundExistentItemInEmporium);
							}
							PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(emporiumItemId).orElseThrow(()-> new SoftNotFoundException("Skin não encontrada"));
							ApprenticeInventoryEntity verifyExistInInventorySecondWave = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId,ItemCatalogInventoryEnum.PET,petOutfitEntity.getPet().getPetId())
									.orElseThrow(()-> new SoftNotFoundException("Falha na conexão de um Pet com a skin dele - Not found"));
						
						String[] collectStepPackage = petOutfitEntity.getPetOutfitPackageSkin().split("_");
								
								PetEntity petEntity = petRepository.findById(verifyExistInInventorySecondWave.getApprenticeInventoryItemId()).orElseThrow(()->new SoftNotFoundException("Pet não permitiu o acesso ao guarda-roupa :("));
								AtomicInteger counter = new AtomicInteger(0);
								petEntity.getOutfits().forEach(outfit->{
									if(counter.get()>=2)return;
									String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
									if(outfitParts[0].length()>0 && outfitParts[0].contains(collectStepPackage[0])) {
									ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = new ApprenticeInventoryEntity();
									
									apprenticeOutfitsInventoryEntity.setApprenticeInventoryItemId(outfit.getPetOutfitId());
									apprenticeOutfitsInventoryEntity.setApprenticeInventoryTag(ItemCatalogInventoryEnum.SKIN);
									apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(false);
									apprenticeOutfitsInventoryEntity.setApprenticeInventoryCreatedAt(LocalDateTime.now());
									apprenticeOutfitsInventoryEntity.setApprentice(userEntity);
									apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
									 counter.incrementAndGet();
									}
								});
							
						

						}
					}
						return new ApiSucessHandler<>(true, "Compra realizada com sucesso!", "SKIN comprada");
					}else {
						throw new ExcededMaxDelimiter("Você já possui esse item");
					}
		
		
			}
	
	public EmporiumStockDTO getStockInEmporium(Long userId,String orderBy){
		 List<StockPetProjection> pets = switch (orderBy != null ? orderBy.toUpperCase() : "") {
	        case "ASC" -> emporiumRepository.getStockPetsInEmporiumASC();
	        case "DESC" -> emporiumRepository.getStockPetsInEmporiumDESC();
	        default -> emporiumRepository.getStockPetsInEmporium();
	    };

	    List<StockPetOutfitProjection> skins = emporiumRepository.getStockOutfitsInEmporium();
	    Set<Long> petIdsInInventory = new HashSet<>(apprenticeInventoryRepository.getIdsFromPetsInInventory(userId));
	    Set<Long> skinIdsInInventory = new HashSet<>(apprenticeInventoryRepository.getIdsFromSkinsInInventory(userId));

	    List<StockPetDTO> stockPetDTOs = pets.stream()
	        .filter(pet -> !petIdsInInventory.contains(pet.getPetId()))
	        .map(pet -> calygamEmporiumMappers.toStockPetDTO(pet))
	        .collect(Collectors.toList());

	    List<StockSkinsDTO> stockSkinDTOs = skins.stream()
	        .filter(skin ->
	            !skinIdsInInventory.contains(skin.getPetOutfitId()) &&
	            petIdsInInventory.contains(skin.getPetId()) 
	        )
	        .map(skin -> calygamEmporiumMappers.toStockSkinsDTO(skin))
	        .collect(Collectors.toList());

	    EmporiumStockDTO emporiumStockDTO = new EmporiumStockDTO();
	    emporiumStockDTO.setPets(stockPetDTOs);
	    emporiumStockDTO.setSkins(stockSkinDTOs);
	    return emporiumStockDTO;
		
		
	}
}
