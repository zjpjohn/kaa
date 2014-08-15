/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.server.common.dao.impl.sql;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kaaproject.kaa.server.common.dao.impl.sql.HibernateDaoConstants.APPLICATION_ALIAS;
import static org.kaaproject.kaa.server.common.dao.impl.sql.HibernateDaoConstants.APPLICATION_PROPERTY;
import static org.kaaproject.kaa.server.common.dao.impl.sql.HibernateDaoConstants.APPLICATION_REFERENCE;
import static org.kaaproject.kaa.server.common.dao.impl.sql.HibernateDaoConstants.LOG_APPENDER_STATUS;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.kaaproject.kaa.common.dto.logs.LogAppenderStatusDto;
import org.kaaproject.kaa.server.common.dao.impl.LogAppenderDao;
import org.kaaproject.kaa.server.common.dao.model.sql.LogAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateLogAppenderDao extends HibernateAbstractDao<LogAppender> implements LogAppenderDao<LogAppender>{

    private static final Logger LOG = LoggerFactory.getLogger(HibernateLogAppenderDao.class);

    @Override
    protected Class<LogAppender> getEntityClass() {
        return LogAppender.class;
    }

    @Override
    public List<LogAppender> findByAppId(String appId) {
        List<LogAppender> appenders = Collections.emptyList();
        LOG.debug("Find log appenders by application id {}", appId);
        if (isNotBlank(appId)) {
            appenders = findListByCriterionWithAlias(APPLICATION_PROPERTY, APPLICATION_ALIAS,
                    Restrictions.and(
                            Restrictions.eq(APPLICATION_REFERENCE, Long.valueOf(appId)),
                            Restrictions.eq(LOG_APPENDER_STATUS, LogAppenderStatusDto.REGISTERED))
                    );
        }
        return appenders;
    }

    @Override
    public List<LogAppender> findAllLogAppendersByAppId(String appId) {
        List<LogAppender> appenders = Collections.emptyList();
        LOG.debug("Find all log appenders by application id {}", appId);
        if (isNotBlank(appId)) {
            appenders = findListByCriterionWithAlias(APPLICATION_PROPERTY, APPLICATION_ALIAS,
                            Restrictions.eq(APPLICATION_REFERENCE, Long.valueOf(appId)));
        }
        return appenders;
    }

    @Override
    public LogAppender registerLogAppenderById(String logAppenderId) {
        LOG.debug("Register log appender with id {}", logAppenderId);
        return updateStatus(logAppenderId, LogAppenderStatusDto.REGISTERED);
    }

    @Override
    public LogAppender unregisterLogAppenderById(String logAppenderId) {
        LOG.debug("Unregister log appender with id {}", logAppenderId);
        return updateStatus(logAppenderId, LogAppenderStatusDto.UNREGISTERED);
    }

    private LogAppender updateStatus(String appenderId, LogAppenderStatusDto status) {
        LogAppender appender = findById(appenderId);
        if(appender!= null){
            appender.setStatus(status);
            appender = save(appender);
        }
        return appender;
    }

}
