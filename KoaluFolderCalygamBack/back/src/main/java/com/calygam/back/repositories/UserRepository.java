package com.calygam.back.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	//caio<- checando se um usuário existe na nossa base
	public Boolean userExistentInMyBase(String email,String Cpf)throws Exception {
		String sqlRead = """
				SELECT user_email,user_cpf FROM tb_users WHERE user_email = ? OR user_cpf = ? 
				""";
		Boolean ExistentUser = false;
		try(Connection con = dataSource.getConnection()){
			try(PreparedStatement stmtRead = con.prepareStatement(sqlRead)){
				stmtRead.setString(1, email);
				stmtRead.setString(2, Cpf);
				try(ResultSet rs = stmtRead.executeQuery()){
					if(rs.next()) {
						ExistentUser = true;
					}
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro no momento de tentar encontrar um usuário :/");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Erro mais exprecifico tentando buscar um usuário :/");
		}
		return ExistentUser;
	}
	
	//caio<- registrando um novo usuário, com os dados fornecidos pela authorizationController
	
	public ResponseEntity<?> CreateOneNewUserInMyBase(String userName,String userEmail,String userHashPassword,String userTelefone,String userCpf,String userRank,String userRole)throws Exception{
		String sqlCreate = """
				INSERT INTO tb_users(user_name,user_email,user_password,user_telefone,user_cpf,user_money,user_rank,user_role) VALUES(?,?,?,?,?,?,?,?)
				""";
		try(Connection con = dataSource.getConnection()){
			try(PreparedStatement stmtCreate = con.prepareStatement(sqlCreate)){
				stmtCreate.setString(1, userName);
				stmtCreate.setString(2, userEmail);
				stmtCreate.setString(3, userHashPassword);
				stmtCreate.setString(4, userTelefone);
				stmtCreate.setString(5, userCpf);
				stmtCreate.setInt(6, 0);
				stmtCreate.setString(7, UserRankEnum.valueOf(userRank).name());
				stmtCreate.setString(8, UserRoleEnum.valueOf(userRole).name());
				stmtCreate.executeUpdate();
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CREATED).body("Erro ao tentar inserir um novo usuário :/");
		
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CREATED).body("Erro mais especifico ao tentar inserir um novo usuário :/");
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("novo usuário cadastrado com sucesso!");
	}
	
	
}
