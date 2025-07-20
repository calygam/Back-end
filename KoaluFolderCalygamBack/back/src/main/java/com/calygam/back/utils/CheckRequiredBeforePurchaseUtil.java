package com.calygam.back.utils;

import org.springframework.stereotype.Component;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.models.CalygamEmporiumEntity;
import com.calygam.back.models.UserEntity;

@Component
public class CheckRequiredBeforePurchaseUtil {
	 
	public Boolean checkRequiredBeforePurchase(UserEntity user, CalygamEmporiumEntity item) {
        if (user == null || item == null) return false;
        if (!item.isEmporiumItemSelling()) return false;

        Long userXp = user.getXp();
        Long userCoins = user.getUserMoney();
        UserRankEnum userRank = UserRankEnum.getRankForXp(userXp);
        UserRankEnum requiredRank = item.getEmporiumItemRankRequired();

  
        //caio<- Verifica se o XP do usuário é suficiente para o rank exigido
        return userXp >= requiredRank.getPoints() && user.getUserMoney()>= item.getEmporiumItemGoldCost();
    }
	
	public Long CheckValidationStock(CalygamEmporiumEntity item) {
        Long qtdOptionsToQtdStock = 0L;
        if(item.getEmporiumItemQtd()<0) {
        	qtdOptionsToQtdStock=0L;
        }else {
        	if(item.getEmporiumItemQtd()==0) {
        		qtdOptionsToQtdStock=1L;
        	}else {
        		qtdOptionsToQtdStock=2L;
        	
        	}
        }
        return qtdOptionsToQtdStock;
	}

}
