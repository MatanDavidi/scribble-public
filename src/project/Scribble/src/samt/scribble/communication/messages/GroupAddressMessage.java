/*
 * The MIT License
 *
 * Copyright 2019 SAMT.
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
package samt.scribble.communication.messages;

import samt.scribble.communication.Commands;
import java.net.InetAddress;

/**
 * Messagio dell'indirizzo del gruppo multicast di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class GroupAddressMessage extends Message {

    /**
     * Crea messaggio dell'indirizzo del gruppo multicast di scribble.
     *
     * @param groupAddress Indirizzo del gruppo multicast di scribble.
     */
    public GroupAddressMessage(InetAddress groupAddress) {
        super(Commands.GROUP_ADDRESS_MESSAGE, groupAddress.getAddress());
    }

}
