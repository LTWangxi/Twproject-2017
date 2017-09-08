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

package com.ericdaugherty.mail.server.services.smtp.server.command.impl;

//Java imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Local imports
import com.ericdaugherty.mail.server.configuration.ConfigurationParameterConstants.CLEAR_TEXT;
import com.ericdaugherty.mail.server.errors.TooManyErrorsException;
import com.ericdaugherty.mail.server.services.smtp.server.COMMAND_VERB;
import com.ericdaugherty.mail.server.services.smtp.server.SMTPServerSessionControl;
import com.ericdaugherty.mail.server.services.smtp.server.action.*;
import com.ericdaugherty.mail.server.services.smtp.server.command.AbstractArgCommand;
import com.ericdaugherty.mail.server.services.smtp.server.parser.impl.EhloInterpreter;

/**
 *
 * @author Andreas Kyrmegalos
 */
public class EhloCommand extends AbstractArgCommand {

   public EhloCommand() {
      super(COMMAND_VERB.EHLO, null, new EhloInterpreter());
      reset();
   }
   
   public void reset() {
      super.reset();
      setPreCommandAction(new EhloPreCommandAction());
      setCommandAction(new EhloCommandAction());
      setPostCommandAction(new EhloPostCommandAction());
   }

   class EhloPreCommandAction extends PreCommandAction {

      public void execute(SMTPServerSessionControl control) {
         control.setDeclaredClientHost(EhloCommand.this.argument);
      }
   }

   class EhloCommandAction extends CommandAction {

      public void execute(SMTPServerSessionControl control) throws TooManyErrorsException, IOException {
         List<String> reply = new ArrayList<String>((int) (6 / 0.75));

         reply.add("250-Hello " + control.getDeclaredClientHost());

         if (!control.isSecured()) {
            reply.add("250-STARTTLS");
         }
         if (control.isMime8bitSupported()) {
            reply.add("250-8BITMIME");
         }
         if (control.isPipeliningSupported()) {
            reply.add("250-PIPELINING");
         }

         String[] authMechs = control.getAuthMechs();
         int mechCount = authMechs.length - (control.getClearTextAllowed() == CLEAR_TEXT.ALWAYS ? 0 : (control.getClearTextAllowed() == CLEAR_TEXT.NEVER ? 2 : (control.isEncrypted() ? 0 : 2)));
         if (mechCount > 0) {
            StringBuilder auth_mech = new StringBuilder();
            for (int i = 0; i < mechCount; i++) {
               auth_mech.append(" ").append(authMechs[i]);
            }
            reply.add("250-AUTH" + auth_mech.toString());
         }

         reply.add("250 SIZE " + (configurationManager.getMaximumMessageSize() * 1024 * 1024));

         control.setMultiReplyLast(reply);
      }
   }

   class EhloPostCommandAction extends PostCommandAction {

      public void execute(SMTPServerSessionControl control) {
         if (!(control.isLastCommand(COMMAND_VERB.RSET)||control.isLastCommand(COMMAND_VERB.EHLO)||
               control.isLastCommand(COMMAND_VERB.HELO))) {
            control.setInitState(false);
         }
      }
   }
}
