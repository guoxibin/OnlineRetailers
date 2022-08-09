package com.example.onlineretailers.roomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonnelInformationDao {
    @Insert
    void insertPersonnelInformation(PersonnelInformation personnelInformation);

    @Delete
    void deletePersonnelInformation(PersonnelInformation personnelInformation);

    @Update
    void updatePersonnelInformation(PersonnelInformation personnelInformation);

    @Query("Update PersonnelInformation set loginState=1 where username =:username")
    void updateLoginState(String username);

    @Query("Update PersonnelInformation set smallChange=:smallChange where loginState = 1")
    void setSmallChange(String smallChange);

    @Query("SELECT * FROM PersonnelInformation")
    List<PersonnelInformation> getPersonnelInformationList();

    @Query("SELECT username FROM PersonnelInformation")
    List<String> getUsernameList();

    @Query("SELECT username FROM PersonnelInformation WHERE loginState = 1")
    String getLoggingUsername();

    @Query("SELECT * FROM PersonnelInformation WHERE loginState = 1")
    PersonnelInformation getPersonnelInformation();

    @Query("SELECT smallChange FROM PersonnelInformation WHERE loginState = 1")
    String getLoginSmallChange();

    @Query("UPDATE PersonnelInformation set smallChange=:smallChange WHERE loginState = 1")
    void setSmallChangeAfterPayment(String smallChange);

    @Query("UPDATE PersonnelInformation set loginState = 0 WHERE username =:username ")
    void setLoginOffline(String username);

}
