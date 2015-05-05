import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.*;
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
    private JButton saveKeyButton;
    private JButton openKeyButton;


    private JEditorPane inputTextField;
    private JEditorPane outputTextField;
    private JPasswordField secretKeyField;
    private JTextField nKeyField;
    private JTextField eKeyField;
    private JButton openFileButton;
    private JButton saveFileButton;
    private JLabel inputOrGenerateNewLabel;
    private JTextField partnerNkey;
    private JTextField partnerEkey;
    private JButton sendKeysButton;
    private JButton sendMessageButton;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JLabel l7;


    private String filePath;
    boolean route = true;

    public static boolean isDigit(String q ){
        for (int i = 0; i < q.length(); i++){
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
        sendKeysButton.setBackground(Color.getColor("myGreen"));
        sendKeysButton.setForeground(Color.WHITE);
        sendMessageButton.setForeground(Color.WHITE);
        sendMessageButton.setBackground(Color.getColor("myGreen"));
//        saveKeyButton.setBackground(Color.getColor("myGreen"));;
//        saveKeyButton.setForeground(Color.WHITE);;
//        openKeyButton.setBackground(Color.getColor("myGreen"));;
//        openKeyButton.setForeground(Color.WHITE);;

        eKey.setForeground(Color.WHITE);
        nKey.setForeground(Color.WHITE);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);
        l6.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        textLabel.setForeground(Color.WHITE);
        crypLabel.setForeground(Color.WHITE);
        selectRouteLabel.setForeground(Color.WHITE);
        inputOrGenerateNewLabel.setForeground(Color.WHITE);

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
        final RSA partnerRsa = new RSA();
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsa.generateKeys();

                nKeyField.setText(String.valueOf(String.valueOf(rsa.getN())));
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
                if(route) {
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
                if (rsa.getE() != null  && rsa.getN()!= null && !inputTextField.getText().isEmpty()) {
                    if (route) {
                        String text = "j";
                        text += inputTextField.getText();
                        BigInteger plaintext = new BigInteger(text.getBytes());
                        partnerRsa.setKey(new BigInteger(partnerEkey.getText()),new BigInteger(partnerNkey.getText()), new BigInteger("0"));
                        BigInteger ciphertext = partnerRsa.encrypt(plaintext);
                        outputTextField.setText(ciphertext.toString());
                    } else {
                        if ( isDigit(inputTextField.getText()) && rsa.getD() != null) {
                            String text = inputTextField.getText();
                            BigInteger plaintext = new BigInteger(text);
                            plaintext = rsa.decrypt(plaintext);
                            String text2 = new String(plaintext.toByteArray());
                            text2 = text2.substring(1, text2.length());
                            outputTextField.setText(text2);
                        }
                        else JOptionPane.showMessageDialog(null, "Bad input parameters");
                    }
                }
                else JOptionPane.showMessageDialog(null, "Bad input parameters");
            }
        });
//        openKeyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                JFileChooser c = new JFileChooser();
//                // Demonstrate "Open" dialog:
//                int rVal = c.showOpenDialog(EncryprionForm.this);
//                if (rVal == JFileChooser.APPROVE_OPTION) {
//                    filePath = c.getSelectedFile().getAbsolutePath();
//                    String sCurrentLine;
//                    BufferedReader br = null;
//                    try {
//                        br = new BufferedReader(new FileReader(filePath));
//                        sCurrentLine = br.readLine();
//                        if(sCurrentLine != null) {
//                            eKeyField.setText(sCurrentLine);
//                            sCurrentLine = br.readLine();
//                            if(sCurrentLine != null) {
//                                nKeyField.setText(sCurrentLine);
//                                sCurrentLine = br.readLine();
//                                if(sCurrentLine != null) {
//                                    secretKeyField.setText(sCurrentLine);
//                                    rsa.setKey(new BigInteger(eKeyField.getText()),new BigInteger(nKeyField.getText()),new BigInteger(sCurrentLine));
//                                }
//                            }
//                        }
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//
//
//                }
//                if (rVal == JFileChooser.CANCEL_OPTION) {
//                    filePath = null;
//                }
//            }
//        });
//        saveKeyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser c = new JFileChooser();
//                // Demonstrate "Save" dialog:
//                int rVal = c.showSaveDialog(EncryprionForm.this);
//                if (rVal == JFileChooser.APPROVE_OPTION) {
//                    filePath = c.getSelectedFile().getAbsolutePath();
//                    PrintWriter writer = null;
//                    try {
//                        writer = new PrintWriter(filePath, "UTF-8");
//                        writer.println(rsa.getE());
//                        writer.println(rsa.getN());
//                        writer.println(rsa.getD());
//                        writer.close();
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    } catch (UnsupportedEncodingException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                if (rVal == JFileChooser.CANCEL_OPTION) {
//                    filePath = null;
//                }
//            }
//        });
//        openFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser c = new JFileChooser();
//                // Demonstrate "Open" dialog:
//                int rVal = c.showOpenDialog(EncryprionForm.this);
//                if (rVal == JFileChooser.APPROVE_OPTION) {
//                    filePath = c.getSelectedFile().getAbsolutePath();
//                    String sCurrentLine;
//                    String allText = "";
//                    BufferedReader br = null;
//                    try {
//                        br = new BufferedReader(
//                                new InputStreamReader(
//                                        new FileInputStream(filePath), "UTF8"));
//                        while ((sCurrentLine = br.readLine()) != null) {
//                            allText += sCurrentLine;
//                        }
//                        inputTextField.setText(allText);
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//
//                    if (rVal == JFileChooser.CANCEL_OPTION) {
//                        filePath = null;
//                    }
//                }
//            }
//
//        });

//        saveFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                JFileChooser c = new JFileChooser();
//                // Demonstrate "Save" dialog:
//                int rVal = c.showSaveDialog(EncryprionForm.this);
//                if (rVal == JFileChooser.APPROVE_OPTION) {
//                    filePath = c.getSelectedFile().getAbsolutePath();
//                    PrintWriter writer = null;
//                    try {
//                        writer = new PrintWriter(filePath, "UTF-8");
//                        writer.println(outputTextField.getText());
//                        writer.close();
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    } catch (UnsupportedEncodingException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                if (rVal == JFileChooser.CANCEL_OPTION) {
//                    filePath = null;
//                }
//
//            }
//        });
        sendKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard c=java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
//копируем текст в буфер обмена
                Transferable data=new StringSelection(eKeyField.getText() + " " + nKeyField.getText());
                c.setContents(data,null);
            }
        });
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard c=java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
//копируем текст в буфер обмена
                Transferable data=new StringSelection(outputTextField.getText());
                c.setContents(data,null);
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
