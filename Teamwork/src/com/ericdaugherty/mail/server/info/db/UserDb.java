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
 * $Rev: 292 $
 * $Date: 2013-03-01 05:55:36 +0100 (Fr, 01 Mrz 2013) $
 *
 ******************************************************************************/

package com.ericdaugherty.mail.server.info.db;

//Java imports
import java.util.HashMap;
import java.util.Map;

//Local imports
import com.ericdaugherty.mail.server.configuration.PasswordFactory;
import com.ericdaugherty.mail.server.configuration.PasswordHasher;
import com.ericdaugherty.mail.server.info.*;

/**
 *
 * @author Andreas Kyrmegalos
 */
public class UserDb extends AbstractUser {

   private int userId;
   private String salt;
   private Map<Realm, char[]> realmPass = new HashMap<Realm, char[]>();

   /**
    * Creates a new user with the full username (user and domain).
    *
    * @param address User's full email address
    */
   public UserDb(EmailAddress address, int userId) {
      super(address);
      this.userId = userId;
   }

   /**
    * Returns true if and only if the specified plain text password's
    * value matches the password for this user.
    *
    * @param plainTextPassword the password to validate.
    * @return true if it matches.
    */
   public boolean isPasswordValid(char[] plainTextPassword) {
      if (log.isDebugEnabled())
        log.debug("Authenticating User: " + getUserAdress());
      PasswordHasher ph = PasswordFactory.getInstance().getPasswordHasher();
      if (salt!=null) {
         ph.setSalt(salt);
      }
      boolean result = ph.passwordMatches(password, plainTextPassword);
      if (log.isDebugEnabled() && !result)
        log.debug("Authentication Failed for User: " + getUserAdress());

      return result;
   }
    
   public int getUserId() {
      return userId;
   }

   public void setSalt(String salt) {
      this.salt = salt;
   }

   public void addRealmPass(Realm realm, char[] password) {
      realmPass.put(realm, password);
   }

   public char[] getRealmPass(Realm realm) {
      return realmPass.get(realm);
   }
}
