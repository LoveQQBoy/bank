package Service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Beam.MemberBeam;
import DAO.MemberDAO;



@Service
@Transactional
public class MembarServiceImpl implements MemberService {
	SessionFactory factory;
	MemberDAO memberDao;
	
	
	@Autowired
	public MembarServiceImpl(SessionFactory factory, MemberDAO memberDao) {
		super();
		this.factory = factory;
		this.memberDao = memberDao;
	}

	public List<MemberBeam> findAll(){
		List<MemberBeam> all=null;
		all=memberDao.findAll();
		return all;
	}

	@Override
	public void save(MemberBeam beam) {
		memberDao.save(beam);
	}
	
	public MemberBeam findById(Integer id) {
		MemberBeam memberBeam = null;

			memberBeam = memberDao.findById(id);

		return memberBeam;
	}
	
	@Override
	public void update(MemberBeam memberBeam) {
			MemberBeam mb = memberDao.findById(memberBeam.getP_ID());
			memberDao.update(memberBeam);

//		}
	}
}
