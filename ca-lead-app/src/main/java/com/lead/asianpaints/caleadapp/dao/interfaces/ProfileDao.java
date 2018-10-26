package com.lead.asianpaints.caleadapp.dao.interfaces;

import java.util.List;

import com.lead.asianpaints.caleadapp.model.Profile;

public interface ProfileDao {
 int createProfile(Profile profile );
 List<Profile>getProfiles();
 
}
