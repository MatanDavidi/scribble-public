/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package samt.scribble.server.modules;

import samt.scribble.communication.GroupConnection;
import samt.scribble.communication.messages.ErrorMessage;
import samt.scribble.communication.messages.GroupAddressMessage;
import samt.scribble.communication.messages.Message;
import samt.scribble.server.player.Player;
import samt.scribble.server.player.PlayerAlreadyRegisteredException;
import samt.scribble.server.player.PlayerManager;

import java.net.DatagramPacket;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Aggiunta di un giocatore alla lista, e ritorno al giocatore l'indirizzo IP
 * del gruppo multicast.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class JoinModule {

    /**
     * Aggiunta di un giocatore alla lista, e ritorno al giocatore l'indirizzo
     * IP del gruppo multicast.
     *
     * @param datagramPacket Dagramma di richiesta join.
     * @param playerManager Gestione dei giocatori di scribble.
     * @param groupConnection Connessione al gruppo multicast.
     * @return Pacchetto di risposta al client.
     */
    public static DatagramPacket join(DatagramPacket datagramPacket, PlayerManager playerManager,
            GroupConnection groupConnection) {
        String nickname = "";
        for (int i = 1; i < datagramPacket.getData().length; i++) {
            if (datagramPacket.getData()[i] != 0) {
                nickname += (char) datagramPacket.getData()[i];
            }
        }

        Player player = new Player(
                nickname,
                datagramPacket.getAddress(),
                DefaultScribbleParameters.DEFAULT_CLIENT_PORT);

        Message message;
        try {
            playerManager.registerPlayer(player);
            message = new GroupAddressMessage(groupConnection.getGroupIp());
        } catch (PlayerAlreadyRegisteredException pare) {
            message = new ErrorMessage(pare.getMessage());
        }

        if (DefaultScribbleParameters.DEBUG_VERBOSITY.equals(DebugVerbosity.Information)) {

            System.out.println(new String(message.getWholeMessage()));

        }

        return new DatagramPacket(
                message.getWholeMessage(),
                message.getWholeMessage().length,
                datagramPacket.getAddress(),
                DefaultScribbleParameters.DEFAULT_CLIENT_PORT);

    }

}
