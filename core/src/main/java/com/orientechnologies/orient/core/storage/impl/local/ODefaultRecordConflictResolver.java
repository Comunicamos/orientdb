/*
 *
 *  *  Copyright 2014 Orient Technologies LTD (info(at)orientechnologies.com)
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *  *
 *  * For more information: http://www.orientechnologies.com
 *  
 */

package com.orientechnologies.orient.core.storage.impl.local;

import com.orientechnologies.orient.core.db.record.ORecordOperation;
import com.orientechnologies.orient.core.exception.OConcurrentModificationException;
import com.orientechnologies.orient.core.exception.OFastConcurrentModificationException;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.version.ORecordVersion;

/**
 * Default strategy: check only for record versions.
 * 
 * @author Luca Garulli
 */
public class ODefaultRecordConflictResolver implements ORecordConflictResolver {
  @Override
  public ORecord<?> onUpdate(ORecordId rid, ORecordVersion iRecordVersion, byte[] iRecordContent, ORecordVersion iDatabaseVersion) {
    if (OFastConcurrentModificationException.enabled())
      throw OFastConcurrentModificationException.instance();
    else
      throw new OConcurrentModificationException(rid, iDatabaseVersion, iRecordVersion, ORecordOperation.UPDATED);
  }
}