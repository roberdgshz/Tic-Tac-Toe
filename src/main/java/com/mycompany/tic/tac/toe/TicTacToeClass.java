
package com.mycompany.tic.tac.toe;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToeClass implements ActionListener{
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton buttons[] = new JButton[9];
    boolean player1_turn;
    
    TicTacToeClass(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(125,125,125));
        
        for(int i=0;i<9;i++){
            buttons[i]=new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        
        title_panel.add(textField);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        firstTurn();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("0´s turn");
                        check();
                    }
                }else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,255));
                        buttons[i].setText("0");
                        player1_turn = true;
                        textField.setText("X´s turn");
                        check();
                    }
                }
            }
        }
    }
    
    public void firstTurn(){
        if(random.nextInt(2)==0){
            player1_turn = true;
            textField.setText("X's turn");
        }else{
            player1_turn = false;
            textField.setText("0's turn");
        }
    }
    
    public void check(){
        String[] texts = new String[buttons.length];
        for(int i=0;i<texts.length;i++){
            texts[i] = buttons[i].getText();
        }
        String[] conditions = {"012","345","678","036","147","258","048","246"};
        
        for(int i=0;i<conditions.length;i++){
            int a = Character.getNumericValue(conditions[i].charAt(0));
            int b = Character.getNumericValue(conditions[i].charAt(1));
            int c = Character.getNumericValue(conditions[i].charAt(2));
            
            if(texts[a].equals(texts[b]) && texts[b].equals(texts[c]) && texts[a] != ""){
                winner(a,b,c,texts[a]);
            }
        }
    }
    
    public void winner(int a,int b,int c,String winner){
        Color color = Color.red;
        if(winner == "X"){
            color = Color.green;
        }
        buttons[a].setBackground(color);
        buttons[b].setBackground(color);
        buttons[c].setBackground(color);
        
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
            textField.setText(winner+" wins!");
        }
    }
}
