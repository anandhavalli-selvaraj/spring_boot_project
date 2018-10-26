package com.lead.asianpaints.caleadapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lead.asianpaints.caleadapp.dao.interfaces.ProfileDao;
import com.lead.asianpaints.caleadapp.model.Profile;




@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int createProfile(Profile profile) {
		KeyHolder keyholder=new GeneratedKeyHolder();
		jdbcTemplate.update((Connection connection)->{
			PreparedStatement ps;
			ps=connection.prepareStatement("INSERT INTO profile (NAME,RUNS,WICKETS) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, profile.getName());
			ps.setString(2, profile.getRuns());
			ps.setString(3, profile.getWickets());
			return ps;
		},keyholder);
		return keyholder.getKey().intValue();
	}

	@Override
	public List<Profile> getProfiles() {
		List<Profile>profileList=new ArrayList<>();
		Collection<Map<String,Object>>result=jdbcTemplate.queryForList("SELECT NAME, RUNS, WICKETS FROM profile");
		result.stream().map((tuple)->{
			Profile pro=new Profile();
			pro.setName((String)tuple.get("NAME"));
			pro.setRuns((String)tuple.get("RUNS"));
			pro.setWickets((String)tuple.get("WICKETS"));
			return pro;
		}).forEach((pro)->{profileList.add(pro);});
		return profileList;
	}

}
