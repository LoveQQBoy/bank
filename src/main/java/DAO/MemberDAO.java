package DAO;

import java.util.List;

import Beam.MemberBeam;


public interface MemberDAO {

	public void save(MemberBeam beam);
	
	public List<MemberBeam> findAll();
	
	public MemberBeam findById(Integer id);
	
	void update(MemberBeam mb);

}