/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ranger.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.ranger.common.db.BaseDao;
import org.apache.ranger.entity.XXSecurityZoneRefService;
import org.apache.ranger.plugin.model.RangerSecurityZone;
import org.apache.ranger.plugin.model.RangerServiceHeaderInfo;

public class XXSecurityZoneRefServiceDao extends BaseDao<XXSecurityZoneRefService>{

	public XXSecurityZoneRefServiceDao(RangerDaoManagerBase daoManager) {
		super(daoManager);
	}

	public List<XXSecurityZoneRefService> findByZoneId(Long zoneId) {
        if (zoneId == null) {
            return null;
        }
        try {
        	List<XXSecurityZoneRefService> xxZoneRefService = getEntityManager()
                    .createNamedQuery("XXSecurityZoneRefService.findByZoneId", tClass)
                    .setParameter("zoneId", zoneId)
                    .getResultList();
            return xxZoneRefService;
        } catch (NoResultException e) {
            return null;
        }
    }

	public List<XXSecurityZoneRefService> findByServiceId(Long serviceId) {
		if (serviceId == null) {
			return Collections.emptyList();
		}
		try {
			return getEntityManager().createNamedQuery("XXSecurityZoneRefService.findByServiceId", tClass)
					.setParameter("serviceId", serviceId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<XXSecurityZoneRefService> findByServiceName(String serviceName) {
		if (serviceName == null) {
			return Collections.emptyList();
		}
		try {
			return getEntityManager().createNamedQuery("XXSecurityZoneRefService.findByServiceName", tClass)
					.setParameter("serviceName", serviceName).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<XXSecurityZoneRefService> findByServiceNameAndZoneId(String serviceName, Long zoneId) {
		if (serviceName == null) {
			return Collections.emptyList();
		}
		try {
			return getEntityManager().createNamedQuery("XXSecurityZoneRefService.findByServiceNameAndZoneId", tClass)
					.setParameter("serviceName", serviceName).setParameter("zoneId", zoneId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

    public List<RangerServiceHeaderInfo> findServiceHeaderInfosByZoneId(Long zoneId) {
        List<RangerServiceHeaderInfo> ret;

        if (zoneId != null && zoneId > RangerSecurityZone.RANGER_UNZONED_SECURITY_ZONE_ID) {
            List<Object[]> results = getEntityManager().createNamedQuery("XXSecurityZoneRefService.findServiceHeaderInfosByZoneId", Object[].class)
			                                           .setParameter("zoneId", zoneId).getResultList();

            ret = new ArrayList<>(results.size());

            for (Object[] result : results) {
                ret.add(new RangerServiceHeaderInfo((Long) result[0], (String) result[1], (String) result[2], (String) result[3], (Boolean) result[4]));
            }
        } else {
            ret = Collections.emptyList();
        }

        return ret;
    }
}
