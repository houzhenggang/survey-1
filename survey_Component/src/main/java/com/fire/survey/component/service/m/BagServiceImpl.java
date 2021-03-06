package com.fire.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.survey.component.dao.i.BagDao;
import com.fire.survey.component.service.i.BagService;
import com.fire.survey.entities.guest.Bag;

@Service
public class BagServiceImpl implements BagService {

	@Autowired
	private BagDao bagDao;

	@Override
	public void save(Bag bag) {
		bagDao.saveEntity(bag);
		bag.setBagOrder(bag.getBagId());
	}

	@Override
	public void deleteBag(Bag bag) {
		bagDao.removeEntity(bag);
	}

	@Override
	public Bag getBag(Integer bagId) {
		return bagDao.getEntityById(bagId);
	}

	@Override
	public void update(Bag bag) {
		String hql = "UPDATE Bag b SET b.bagName=? WHERE b.bagId = ?";
		bagDao.updateEntityByHql(hql, bag.getBagName(), bag.getBagId());
	}

	@Override
	public void batchUpdateOrder(List<Integer> orderIds, List<Integer> orders) {
		String sql = "UPDATE survey_bag set bag_order = ? where bag_id = ?";
		Object[][] params = new Object[orderIds.size()][2];
		for (int i = 0; i < params.length; i++) {
			orders.get(i);
			params[i] = new Object[] { orders.get(i), orderIds.get(i) };
		}
		bagDao.batchUpdate(sql, params);
	}

}
