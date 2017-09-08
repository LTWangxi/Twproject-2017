/******************************************************************************
 * This program is a 100% Java Email Server.
 ******************************************************************************
 * Copyright (c) 2001-2013, Eric Daugherty (http://www.ericdaugherty.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the copyright holder nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 ******************************************************************************
 * For current versions and more information, please visit:
 * http://javaemailserver.sf.net/
 *
 * or contact the author at:
 * andreaskyrmegalos@hotmail.com
 *
 ******************************************************************************
 * This program is based on the CSRMail project written by Calvin Smith.
 * http://crsemail.sourceforge.net/
 ******************************************************************************
 *
 * $Rev$
 * $Date$
 *
 ******************************************************************************/

package com.ericdaugherty.mail.server.configuration.cbc;

//Java imports
import java.util.*;

//Local imports
import com.ericdaugherty.mail.server.configuration.ConfigurationManager;

//Other imports
import com.ericdaugherty.mail.server.JSON.JSONObject;

/**
 *
 * @author Andreas Kyrmegalos
 */
public final class RetrieveConfiguration extends CBCResponseExecutor {

   public RetrieveConfiguration(ListIterator<String> iter) {
      super(iter);
   }

   public final byte[] processLines() throws CBCResponseException {

      String line;
      for (; iter.hasNext();) {
         line = iter.next();
         log.debug(line);
         Map<String, String> configurationMap;
         if (line.startsWith(CONFIG_GENERAL)) {
            configurationMap = ConfigurationManager.getInstance().getGeneralConfiguration();
            log.debug("General settings requested");
         } else if (line.startsWith(CONFIG_BACKEND)) {
            configurationMap = ConfigurationManager.getInstance().getBackendConfiguration();
            log.debug("Backend settings requested");
         } else if (line.startsWith(CONFIG_MAIL)) {
            configurationMap = ConfigurationManager.getInstance().getMailConfiguration();
            log.debug("Mail settings requested");
         } else if (line.startsWith(CONFIG_DIR)) {
            configurationMap = ConfigurationManager.getInstance().getDirConfiguration();
            log.debug("Directory settings requested");
         } else if (line.startsWith(CONFIG_AMAVIS)) {
            configurationMap = ConfigurationManager.getInstance().getAmavisConfiguration();
            log.debug("Amavis settings requested");
         } else if (line.startsWith(CONFIG_OTHER)) {
            configurationMap = new HashMap<String,String>();
            configurationMap.put("jes.fileSystem", ConfigurationManager.isWin()?"Win":"Linux");
            configurationMap.put("jes.version", System.getProperty("jes.version"));
            log.debug("Other settings requested");
         } else {
            continue;
         }
         String configuration = new JSONObject(configurationMap).toString();
         byte[] response = configuration.getBytes();
         return response;
      }
      throw new CBCResponseException();
   }
}
