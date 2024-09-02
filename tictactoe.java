import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class tictactoe {
    int boardwidth=650;
    int boardHeight=650;
    JFrame frame= new JFrame("tic tac toe");
    JLabel textLable= new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPanel= new JPanel();
    JButton[][] board= new JButton[3][3];
    JButton restartButton= new JButton();
    String playerX="X";
    String playerY="O";
    String currentPlayer= playerX;
    boolean gameOver= false;
    int turns=0;
    tictactoe(){
        frame.setVisible(true);
        frame.setSize(boardwidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLable.setBackground(Color.darkGray);
        restartButton.setBackground(Color.red);
        restartButton.setForeground(Color.black);
        restartButton.setLayout(new BorderLayout());
        restartButton.setFont(new Font("Arial",Font.BOLD, 30));
        textLable.setBackground(Color.white);
        textLable.setFont(new Font("Arial",Font.BOLD, 50));
        textLable.setHorizontalAlignment(JLabel.CENTER);
        textLable.setText("tic tac toe");
        restartButton.setText("Restart Game");
        textLable.setOpaque(true);
        textPanel.add(textLable);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(restartButton, BorderLayout.SOUTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        restartButton.addActionListener(
            e->restart());

        for(int r= 0; r<3; r++){
            for(int c=0;c<3; c++){
                JButton tile= new JButton();
                board[r][c]= tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font ("Arial", Font.BOLD,120));
                tile.setFocusable(false);
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile= (JButton)e.getSource();
                        if(tile.getText()== ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer=(currentPlayer == null ? playerX == null : currentPlayer.equals(playerX)) ? playerY : playerX;
                                textLable.setText(currentPlayer+"'s turn.");
                            }
                        }
                    }
                });
            }
        } 
    }
     void checkWinner(){
        //horizontal
        for(int r=0;r<3;r++){
            if(board[r][0].getText()=="") continue;
            if (board[r][0].getText().equals(board[r][1].getText())&&
            board[r][1].getText()== board[r][2].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
            gameOver=true;
            return;
            }
        }
        //vertical
        for(int c=0; c<3;c++){
            if(board[0][c].getText()=="") continue;
            if (board[0][c].getText()== board[1][c].getText()&& 
            board[1][c].getText()== board[2][c].getText()){
                for(int i=0; i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
          }
        }
        // diagonally
        if(board[0][0].getText()==board[1][1].getText()&&
        board[1][1].getText()== board[2][2].getText()&&
        board[0][0].getText()!=""){
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }
        if(board[0][2].getText()== board[1][1].getText()&&
        board[1][1].getText()==board[2][0].getText()&&
        board[0][2].getText()!=""){
            for(int i=0; i<3; i++){
                setWinner(board[i][2-i]);
            }
            gameOver=true;
            return;
        }
        if(turns==9){
            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver=true;
        }
     }
     void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLable.setText(currentPlayer+" player is winning");
     }
     void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.GRAY);
        textLable.setText("TIE");
     }
     void restart() {
        currentPlayer = playerX;
        gameOver = false;
        turns = 0;
        textLable.setText(currentPlayer + "'s turn.");

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = board[r][c];
                tile.setText("");
                tile.setForeground(Color.white);
                tile.setBackground(Color.darkGray);
            }
        }
    }
 }