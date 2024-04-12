import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//importing all libraries 

public class TicTacToe {
    //initializing all variables 
    int boardWidth = 600;
    int boardHeight = 650; //50 px for text panel, to display turn and who won or lost

    //create window
    JFrame frame = new JFrame("Tic-Tac-Toe"); //giving title for our window 

    //adding panel 
    //for text label is required 
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    //creating the board
    JPanel boardPanel = new JPanel();

    //adding tiles
    //creating 2d array for adding each tile , by making it a button 
    JButton [][] board = new JButton[3][3]; //grid contains 3x3 hence array also has same size

    //each button has text X or O , so creating a string 
    //making two variables for two players 
    String playerX = "X"; //string 1
    String playerO = "O"; //string 2
    String currentPlayer = playerX;  //indicates the current player 


    //checking for winners
    boolean gameOver = false;  //initially gameOver is not true 

    //to check for tie 
    int turns = 0;





    //default constructor
    TicTacToe(){
        frame.setVisible(true); //to make the frame visible 
        frame.setSize(boardWidth, boardHeight); // setting size of the frame equal to the height and width
        frame.setLocationRelativeTo(null); //making sure that frame will always be in center 
        frame.setResizable(false); //making sure that frame size does not change 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // making sure frame exits when closed
        frame.setLayout(new BorderLayout());  //border is kept at the top 

        //settings for textLabel
        textLabel.setBackground(Color.darkGray); //makes the background grey
        textLabel.setForeground(Color.white);    //makes font color white 
        textLabel.setFont(new Font("Arial", Font.BOLD, 50)); //setting font style , making it bold and making it 50 size
        textLabel.setHorizontalAlignment(JLabel.CENTER); // it makes our text start at center rather than left
        textLabel.setText("Tic-Tac-Toe"); //giving default text to label 
        textLabel.setOpaque(true); // making it opaque or solid color 

        //adding the label to textPanel
        textPanel.setLayout(new BorderLayout());  //adding border for the panel
        textPanel.add(textLabel); //adding the textLabel to the panel

        //adding the panel to the frame 
        frame.add(textPanel, BorderLayout.NORTH);  //pushes the panel upwards

        //giving features for board panel 
        boardPanel.setLayout(new GridLayout(3,3)); //creating grid with 3 rows and columns
        boardPanel.setBackground(Color.black);  //setting background color of board 
        frame.add(boardPanel); //adding the boardPanel to frame or window


        //adding the buttons , loop will be required 
        for(int r =0; r<3; r++){          //r stands for row
            for(int c = 0;c<3;c++){       //c stands for column
                JButton tile = new JButton();   //creating a tile button
                board[r][c] = tile;     //button tile will have array having r and c
                boardPanel.add(tile);   //adding button tile to the board panel 

                //adding properties to the button tile 
                tile.setBackground( Color.black);  //button color 
                tile.setForeground(Color.white);   //font color for tile button 
                tile.setFont(new Font("Arial", Font.BOLD, 120)); //setting font style and size
                tile.setFocusable(false); //no focus 
                //tile.setText(currentPlayer); //text will be the string of currentPlayer 

                //we need to set the text to either X or O for each tile 
                //for that we need to add action listener for each tile 

                tile.addActionListener(new ActionListener(){
                    //creating a function 

                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return; // if gameOver is true then it will stop 

                        //action will only come from button clicks 
                        JButton tile = (JButton) e.getSource(); //the object will be from button
                        if(tile.getText() == ""){
                        tile.setText(currentPlayer);  //the text will be only x because that is current
                        turns++;    //increment the number of turns 
                        checkWinner();  //we will check 2D array for winning conditions 

                        if(!gameOver){
                            //to alternate the currentPlayer text 
                            currentPlayer = currentPlayer == playerX ? playerO : playerX;
                            //if player current is X alternate to player O otherwise X only
                            //update the textLabel
                            textLabel.setText(currentPlayer + "'s turn.");  //telling whose turn it is 
                        }
                    }
                }
            });



            }
        }

    


    }


void checkWinner() {
    //checking winning conditions 
    //HORIZONTAL CONDITION 
    for(int r =0; r< 3;r++){
        if(board[r][0].getText() == "") continue;   //first check if empty , if empty does not proceed

        if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()){
            
            for(int i=0;i<3;i++){
                setWinner(board[r][i]);   //i is the font color 
            }
            gameOver = true;  // one tile should be equal to text in another , then game over
            return; 
        }
    }
    //VERTICAL CONDITION
    for(int c=0;c<3;c++){
        if(board[0][c].getText()=="") continue;  //first check if empty 
        if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()){
            for(int i=0;i<3;i++){
                setWinner(board[i][c]);  //i is the font color 
            }
            gameOver = true; //one tile should be equal to text in another , then game over 
            return;
        }
    }
    //DIAGONAL CONDITION 
    if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
        for(int i=0;i<3;i++){
            setWinner(board[i][i]);  // i is the font color 
        }
        gameOver = true;
        return;
    }
    //ANTI-DIAGONAL CONDITION 
    if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
       
            setWinner(board[0][2]);  // i is the font color 
            setWinner(board[1][1]);
            setWinner(board[2][0]);
        
        gameOver = true;
        return;
    }
    //CHECKING FOR TIE CONDITION
    if(turns==9){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                setTie(board[r][c]);
            }
        }
        gameOver = true;
    }

}
void setWinner(JButton tile){    //this is for changing the font color for winner 
   tile.setForeground(Color.yellow);    //match will have yellow font in horizontal 
   tile.setBackground(Color.gray);  // match will have gray background 
   textLabel.setText(currentPlayer + "is the Winner !!");  //updating the text to say game is over 


}
void setTie(JButton tile){
    tile.setForeground(Color.green);    //match will have green font 
    tile.setBackground(Color.gray);  // match will have gray background 
    textLabel.setText("Tie");  //updating the text to say game is over 


}
    
}
