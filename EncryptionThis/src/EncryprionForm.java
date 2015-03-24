import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * Created by komarov on 24.03.2015.
 */
public class EncryprionForm extends JFrame{
    private JPanel crypPanel;
    private JPanel rootPanel;
    private JPanel genKeyPanel;

    private JLabel eKey;
    private JLabel nKey;
    private JLabel secretKey;
    private JLabel textLabel;
    private JLabel crypLabel;
    private JLabel selectRouteLabel;

    private JButton generateButton;
    private JButton formCrypButton;
    private JButton backButton;
    private JButton executeButton;
    private JButton routeButton;


    private JEditorPane inputTextField;
    private JEditorPane outputTextField;
    private JTextField secretKeyField;
    private JTextField nKeyField;
    private JTextField eKeyField;


    boolean route = true;

    public static boolean isDigit(String q ){
        for (int i = 0; i < q.length(); i++)
        {
            if(!Character.isDigit(q.charAt(i)))
                return false;
        }
        return true;
    }

    public void setStyle(){
        System.setProperty("myBlack", "0X404244");
        System.setProperty("myGreen", "0X5caa15");
        rootPanel.setBackground(Color.getColor("myBlack"));
        crypPanel.setBackground(Color.getColor("myBlack"));
        genKeyPanel.setBackground(Color.getColor("myBlack"));

        generateButton.setBackground(Color.getColor("myGreen"));
        generateButton.setForeground(Color.WHITE);
        formCrypButton.setBackground(Color.getColor("myGreen"));
        formCrypButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.getColor("myGreen"));
        backButton.setForeground(Color.WHITE);
        executeButton.setBackground(Color.getColor("myGreen"));
        executeButton.setForeground(Color.WHITE);
        routeButton.setBackground(Color.getColor("myGreen"));
        routeButton.setForeground(Color.WHITE);

        eKey.setForeground(Color.WHITE);
        nKey.setForeground(Color.WHITE);
        secretKey.setForeground(Color.WHITE);
        textLabel.setForeground(Color.WHITE);
        crypLabel.setForeground(Color.WHITE);
        selectRouteLabel.setForeground(Color.WHITE);

        genKeyPanel.setVisible(true);
        crypPanel.setVisible(false);
    }

    public EncryprionForm(){
        super("EncryptionThis");
        setContentPane(rootPanel);
        pack();
        setStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final int bits = 1024;
        final RSA rsa = new RSA();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsa.generateKeys();

                nKeyField.setText(String.valueOf(rsa.getN()));
                eKeyField.setText(String.valueOf(rsa.getE()));
                secretKeyField.setText(String.valueOf(rsa.getD()));
            }
        });


        formCrypButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKeyPanel.setVisible(false);
                crypPanel.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genKeyPanel.setVisible(true);
                crypPanel.setVisible(false);
            }
        });
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        routeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(route)
                {
                    routeButton.setText("Decryption");
                    textLabel.setText("Crypto-Text");
                    crypLabel.setText("Text");
                    route = false;
                }
                else {
                    routeButton.setText("Encryption");
                    textLabel.setText("Text");
                    crypLabel.setText("Crypto-Text");
                    route = true;
                }
            }
        });
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rsa.getE() != null && rsa.getD() != null && rsa.getN()!= null) {
                    if (route) {
                        String text = inputTextField.getText();
                        BigInteger plaintext = new BigInteger(text.getBytes());

                        BigInteger ciphertext = rsa.encrypt(plaintext);
                        outputTextField.setText(ciphertext.toString());
                    } else if(isDigit(inputTextField.getText())) {
                        String text = inputTextField.getText();
                        BigInteger plaintext = new BigInteger(text);
                        plaintext = rsa.decrypt(plaintext);
                        String text2 = new String(plaintext.toByteArray());
                        outputTextField.setText(text2);
                    }
                }
            }
        });
    }


}
