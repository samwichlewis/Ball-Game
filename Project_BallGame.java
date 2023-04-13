/*
Project
Creates a ball game.
by Sam Lewis
*/

import java.util.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.layout.GridPane.*;

//ball canvas
class BallCanvas extends Canvas
{
   //constructor
   public BallCanvas()
   {
      setWidth(80);
      setHeight(80);
   }
   
   //draw circle
   public void drawCircle()
   {
      GraphicsContext gc = getGraphicsContext2D();
      gc.setFill(Color.CRIMSON); //red
      gc.fillOval(0,0,80,80);
      gc.setFill(Color.ORANGE); //orange
      gc.fillOval(5,5,70,70);
      gc.setFill(Color.GOLD); //yellow
      gc.fillOval(10,10,60,60);
      gc.setFill(Color.FORESTGREEN); //green
      gc.fillOval(15,15,50,50);
      gc.setFill(Color.ROYALBLUE); //blue
      gc.fillOval(20,20,40,40);
      gc.setFill(Color.INDIGO); //purple
      gc.fillOval(25,25,30,30);
      
   }
   
   //clear circle
   public void clearCircle()
   {
      GraphicsContext gc = getGraphicsContext2D();
      gc.clearRect(0,0,80,80);
   }
} 


//CLIENT =====================================================================================
public class Project_BallGame extends Application
{   
   //instance variables
   private BorderPane root = new BorderPane(); //root border pane
   private GridPane gp = new GridPane(); //gridpane
   private GamePane [][] gameList = new GamePane [4][4];
   private Label label = new Label("");
   
   //stage
   public void start(Stage stage)
   {
      //creating Game Panes and adding them to Grid Pane
      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
            gameList[i][j] = new GamePane(i,j);
            gp.add(gameList[i][j],i,j);
         }
      }
      
      //starting board
      gameList[0][2].setBallVisible(false);
      gameList[0][0].setTopButtonVisible(true);
      gameList[2][2].setRightButtonVisible(true);
      gameList[0][0].drawGamePane();
      
      //gridpane spacing
      gp.setHgap(10);
      gp.setVgap(10);
      gp.setAlignment(Pos.CENTER);
      
      //adding to root
      root.setTop(label);
      root.setCenter(gp);
      
      //setting scene
      Scene scene = new Scene(root, 600, 600);
      stage.setScene(scene);
      stage.setTitle("The Ball Game");
      stage.show();
   }
   
   
//GAMEPANE =====================================================================================
   //gamepane
   public class GamePane extends GridPane 
   {
      //visibility booleans
      private boolean ballVisible = true; //boolean for if the ball is visible
      private boolean topButtonVisible = false; //boolean for if the top button is visible
      private boolean bottomButtonVisible = false; //boolean for if the bottom button is visible
      private boolean leftButtonVisible = false; //boolean for if the left button is visible
      private boolean rightButtonVisible = false; //boolean for if the right button is visible
      
      //variables
      private BallCanvas cc = new BallCanvas();
      private Button top = new Button(""); //top
      private Button bottom = new Button("");
      private Button left = new Button(""); //left
      private Button right = new Button(""); //right
      private int xPos = 0;
      private int yPos = 0;
      private int numOfBalls = 0;
      private int numOfMoves = 0;
      
      //constructor
      public GamePane (int i, int j)
      {
       xPos = i;
       yPos = j;
       
       //creating buttons 
         //top
         top.setPrefSize(80,20); //size
         top.setOnAction(new ButtonListener()); //action
         
         //bottom
         bottom.setPrefSize(80,20); //size
         bottom.setOnAction(new ButtonListener()); //action
         
         //left
         left.setPrefSize(20,80); //size
         left.setOnAction(new ButtonListener()); //action
         
         //right
         right.setPrefSize(20,80); //size
         right.setOnAction(new ButtonListener()); //action
         
         //adding buttons
         add(left,0,1);
         add(top,1,0);
         add(bottom,1,2);
         add(right,2,1);
         
         //creating and adding canvas circles
         add(cc,1,1);
         cc.drawCircle();
      }
      
      //get ball visiblility
      public boolean getBallVisible()
      {
         return ballVisible;
      }
      
      //change ball visiblility
      public void setBallVisible(boolean ballVisible_in)
      {
         ballVisible = ballVisible_in;
      }
      
      //get top button visibility
      public boolean getTopButtonVisible()
      {
         return topButtonVisible;
      }
      
      //change top button visiblility
      public void setTopButtonVisible(boolean topVisible_in)
      {
         topButtonVisible = topVisible_in;
      }
      
      //get bottom button visibility
      public boolean getBottomButtonVisible()
      {
         return bottomButtonVisible;
      }
      
      //change bottom button visiblility
      public void setBottomButtonVisible(boolean bottomVisible_in)
      {
         bottomButtonVisible = bottomVisible_in;
      }
      
      //get left button visibility
      public boolean getLeftButtonVisible()
      {
         return leftButtonVisible;
      }
      
      //change left button visiblility
      public void setLeftButtonVisible(boolean leftVisible_in)
      {
         leftButtonVisible = leftVisible_in;
      }
      
      //get right button visibility
      public boolean getRightButtonVisible()
      {
         return rightButtonVisible;
      }
      
      //change right button visiblility
      public void setRightButtonVisible(boolean rightVisible_in)
      {
         rightButtonVisible = rightVisible_in;
      }
      
      //counting the number of balls left
      public void ballsLeft()
      {
         numOfBalls = 0;
         for(int i = 0; i < 4; i++)
         {
            for (int j = 0; j < 4; j++)
            {
               if (gameList[i][j].getBallVisible() == true)
                  numOfBalls++;
            }
         }
      }
      
      //counting the number of moves left
      public void movesLeft()
      {
         numOfMoves = 0;
         
         for(int i = 0; i < 4; i++)
         {
            for (int j = 0; j < 4; j++)
            {
               //top
               if (gameList[i][j].getTopButtonVisible() == true)
                  numOfMoves++;
               
               //bottom
               if (gameList[i][j].getBottomButtonVisible() == true)
                  numOfMoves++;
               
               //left
               if (gameList[i][j].getLeftButtonVisible() == true)
                  numOfMoves++;
               
               //right
               if (gameList[i][j].getRightButtonVisible() == true)
                  numOfMoves++;
            }
         }
      }
      
      //drawing game pane
      public void drawGamePane()
      {
         for(int i = 0; i < 4; i++)
         {
            for (int j = 0; j < 4; j++)
            {
               //changing ball visibility
               if (gameList[i][j].getBallVisible() == true)
                  gameList[i][j].cc.drawCircle();
               else 
                  gameList[i][j].cc.clearCircle();
               
               //changing button visibility
               gameList[i][j].top.setVisible(gameList[i][j].getTopButtonVisible());
               gameList[i][j].bottom.setVisible(gameList[i][j].getBottomButtonVisible());
               gameList[i][j].left.setVisible(gameList[i][j].getLeftButtonVisible());
               gameList[i][j].right.setVisible(gameList[i][j].getRightButtonVisible());
            }
         }
         
         //counting number of balls and moves left
         ballsLeft();
         movesLeft();
         
         //changing label
         label.setText(" Balls left: " + numOfBalls + "    Number of moves: " + numOfMoves);
         
         //checking if lose?
         if (numOfMoves == 0)
            label.setText(" You lose!");
         
         //checking if win?
         if (numOfBalls == 1)
            label.setText(" You win!");
      }
      
      
  //IS MOVE VALID? ===================================================================================
      //checks if the move is valid and makes the buttons visible if so
      public void isMoveValid(int x, int y)
      {
         //setting buttons to not visible
         gameList[x][y].setTopButtonVisible(false);
         gameList[x][y].setBottomButtonVisible(false);
         gameList[x][y].setLeftButtonVisible(false);
         gameList[x][y].setRightButtonVisible(false);
         
         //checking if ball can move down (top button)
         try
         {
            if (gameList[x][y].getBallVisible() == true && gameList[x][y+1].getBallVisible() == true 
            && gameList[x][y+2].getBallVisible() == false)
            {
               gameList[x][y].setTopButtonVisible(true);
            }
         }
         catch (ArrayIndexOutOfBoundsException aioobe) {}
         
         //checking if ball can move up (bottom button)
         try
         {
            if (gameList[x][y].getBallVisible() == true && gameList[x][y-1].getBallVisible() == true 
            && gameList[x][y-2].getBallVisible() == false)
            {
               gameList[x][y].setBottomButtonVisible(true);
            }
         }
         catch (ArrayIndexOutOfBoundsException aioobe) {}
         
         //checking if ball can move right (left button)
         try
         {
            if (gameList[x][y].getBallVisible() == true && gameList[x+1][y].getBallVisible() == true 
            && gameList[x+2][y].getBallVisible() == false)
            {
               gameList[x][y].setLeftButtonVisible(true);
            }
         }
         catch (ArrayIndexOutOfBoundsException aioobe) {}
         
         //checking if ball can move left (right button)
         try
         {
            if (gameList[x][y].getBallVisible() == true && gameList[x-1][y].getBallVisible() == true 
            && gameList[x-2][y].getBallVisible() == false)
            {
               gameList[x][y].setRightButtonVisible(true);
            }
         }
         catch (ArrayIndexOutOfBoundsException aioobe) {}
      }
            
      
  //BUTTON LISTENER ===================================================================================
      //if buttons clicked
      public class ButtonListener implements EventHandler<ActionEvent> 
      {
         public void handle(ActionEvent ae) 
         {            
            //top button clicked
            if (ae.getSource() == top)
            {
               gameList[xPos][yPos].setBallVisible(false);
               gameList[xPos][yPos+1].setBallVisible(false);
               gameList[xPos][yPos+2].setBallVisible(true);
            }
            
            //bottom button clicked
            if (ae.getSource() == bottom)
            {
               gameList[xPos][yPos].setBallVisible(false);
               gameList[xPos][yPos-1].setBallVisible(false);
               gameList[xPos][yPos-2].setBallVisible(true);
            }
            
            //left button clicked
            if (ae.getSource() == left)
            {
               gameList[xPos][yPos].setBallVisible(false);
               gameList[xPos+1][yPos].setBallVisible(false);
               gameList[xPos+2][yPos].setBallVisible(true);
            }
            
            //right button clicked
            if (ae.getSource() == right)
            {
               gameList[xPos][yPos].setBallVisible(false);
               gameList[xPos-1][yPos].setBallVisible(false);
               gameList[xPos-2][yPos].setBallVisible(true);
            }
            
            //changing button visibility
            for (int i = 0; i < 4; i++)
            {
               for (int j = 0; j < 4; j++)
               {
                  isMoveValid(i,j);
               }
            }
            
            drawGamePane(); //redraw the game pane after move is made
         }
      }
   }
   
   
   //main
   public static void main(String[] args)
   {
      launch(args);
   }
}
