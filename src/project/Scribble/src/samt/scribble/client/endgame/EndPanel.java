/*
 * The MIT License
 *
 * Copyright 2019 Bryan.
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
package samt.scribble.client.endgame;

/**
 * La classe EndPanel è una sottoclasse di JPanel che contiene il messaggio di
 * fine partita, con un bottone che, una volta cliccato, riporta al pannello di
 * accesso (vedi {@link samt.scribble.client.login.LoginPanel LoginPanel}).
 *
 * @author bryanbeffa
 * @author MatanDavidi
 * @version 1.0.1 (2019-05-17 - 2019-05-20)
 */
public class EndPanel extends javax.swing.JPanel {

    /**
     * L'EndGameListener che riceverà gli eventi relativi a quest'istanza di
     * EndPanel.
     */
    private EndGameListener listener;

    /**
     * Creates new form EndPanel
     */
    public EndPanel() {
        initComponents();
    }

    /**
     * Imposta un testo contenuto all'interno del guesserLabel.
     *
     * @param text Il testo da inserire nel guesserLabel.
     */
    public void setGuesserLabelText(String text) {
        guesserLabel.setText(text);
    }

    /**
     * Imposta un EndGameListener (vedi
     * {@link samt.scribble.client.endgame.EndGameListener EndGameListener}) che
     * riceverà le chiamate ai metodi relativi agli eventi sollevati da
     * quest'istanza di EndPanel.
     *
     * @param listener L'EndGameListener che riceverà gli eventi relativi a
     * quest'istanza di EndPanel.
     */
    public void setListener(EndGameListener listener) {
        this.listener = listener;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        replayButton = new javax.swing.JButton();
        guesserLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        replayButton.setText("Gioca Ancora");
        replayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                replayButtonMouseClicked(evt);
            }
        });
        add(replayButton, java.awt.BorderLayout.PAGE_END);

        guesserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(guesserLabel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void replayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_replayButtonMouseClicked
        listener.endPanelClicked();
    }//GEN-LAST:event_replayButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel guesserLabel;
    private javax.swing.JButton replayButton;
    // End of variables declaration//GEN-END:variables
}
