package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Beam.MemberBeam;






@Repository
public class MemberDAOImpl implements MemberDAO {
	SessionFactory factory;

	
	
	@Autowired
	public MemberDAOImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	public List<MemberBeam> findAll(){
		List<MemberBeam> find= null;
		
		String hql="from MemberEntity";
		Session session = factory.getCurrentSession();
		find = session.createQuery(hql,MemberBeam.class).getResultList();
		
		return find;
		
	}
	
	@Override
	public void save(MemberBeam beam) {
		Session session=factory.getCurrentSession();
		session.save(beam);
	}
	
	public MemberBeam findByMemberId(String id) {
		MemberBeam result = null;
		String hql = "FROM MemberEntity me WHERE me.memberId = :mid";
		Session session = factory.getCurrentSession();
		List<MemberBeam> beans = session.createQuery(hql, MemberBeam.class)
						       			.setParameter("mid", id)
						       			.getResultList();
		if ( !beans.isEmpty()) {
			result = beans.get(0);
		}
		return result;
	}
	
	@Override
	public MemberBeam findById(Integer id) {
		Session session = factory.getCurrentSession();
		MemberBeam memberBeam = session.get(MemberBeam.class, id);
		return memberBeam;
	}
	
	@Override
	public void update(MemberBeam memberBeam) {
//		Session session = factory.getCurrentSession();
////		session.saveOrUpdate(memberBean);
//		session.merge(memberBean);
		Session session = factory.getCurrentSession();
		String hql = " UPDATE MemberEntity m SET m.username = :username, m.account = :account, "
			       + " m.password = :password, m.bornDate = :bornDate, m.identityCard = :identityCard, "
			       + " m.phoneNumber = :phoneNumber , m.email = :email"
				   + " WHERE m.P_ID = :P_ID";
		session.createQuery(hql)
		       .setParameter("username", memberBeam.getUsername())
		       .setParameter("account", memberBeam.getAccount())
		       .setParameter("password", memberBeam.getPassword())
		       .setParameter("bornDate", memberBeam.getBornDate())
		       .setParameter("identityCard", memberBeam.getIdentityCard())
		       .setParameter("phoneNumber", memberBeam.getPhoneNumber())
		       .setParameter("email", memberBeam.getEmail())
		       .setParameter("P_ID", memberBeam.getP_ID())
		       .executeUpdate();
	}
}
