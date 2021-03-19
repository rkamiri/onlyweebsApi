package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<AuthEntity, String> { }
