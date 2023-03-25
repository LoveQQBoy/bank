package Service;

import java.util.List;

import Beam.MemberBeam;

public interface MemberService {

	void save(MemberBeam beam);

	public List<MemberBeam> findAll();
	
	public MemberBeam findById(Integer id);
	
	public void update(MemberBeam memberBeam);
}