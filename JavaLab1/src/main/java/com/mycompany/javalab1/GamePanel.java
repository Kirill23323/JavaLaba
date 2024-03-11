/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.javalab1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;

/**
 *
 * @author Nushi
 */
public class GamePanel extends javax.swing.JPanel {

    // Variables
    public int firstTargetX = 300,firstTargetY = 175;
    public int secondTargetX = 400,secondTargetY = 175;
    public int firstTargetRadius = 20,secondTargetRadius = 10;
    public int score,shotsCount = 0;
    public int arrowX = 60,arrowY = 170;
    public int [] arrowXPoints ={arrowX,arrowX + 15,arrowX + 15,arrowX + 30, arrowX + 15, arrowX + 15, arrowX};
    public int [] arrowYPoints = {arrowY, arrowY, arrowY - 5, arrowY + 6,arrowY + 17, arrowY + 12, arrowY + 12};
    public Polygon player;
    public Polygon arrow;
    public boolean arrowInFlight = false;
    public boolean theGameIsOn = false;
    
    
    public GamePanel() {
        setPreferredSize(new Dimension(610, 393));
        player = new Polygon(
                    new int[]{50, 30, 30},
                    new int[]{175, 150, 200},
                    3);
        arrow = new Polygon(arrowXPoints,arrowYPoints,arrowXPoints.length);
        initComponents();
    }
    
    public void MoveFirstTarget(int x,int y){
        this.firstTargetX = x;
        this.firstTargetY = y;
        repaint();
    }
    public void MoveSecondTarget(int x,int y){
        this.secondTargetX = x;
        this.secondTargetY = y;
        repaint();
    }
    public void next(){
        if(arrowInFlight){
            MoveArrow(30);
            if(arrowX >= 440){
                ReturnArrow();
            }
        }
        MoveFirstTarget(firstTargetX,firstTargetY + 10);
        if(firstTargetY > 330){
            MoveFirstTarget(firstTargetX,0);
        }
        MoveSecondTarget(secondTargetX,secondTargetY + 20);
        if(secondTargetY > 350){
            MoveSecondTarget(secondTargetX,0);
        }
        CheckCollision();
    }
    public void MoveArrow(int k){
        this.arrowX += k;
        for(int i = 0;i < arrowXPoints.length;i++){
            arrowXPoints[i] += k;
        }
        UpdateArrowPolygon();
        repaint();
    }
    public void UpdateArrowPolygon() {
        arrow = new Polygon(arrowXPoints, arrowYPoints, arrowXPoints.length);
    }
    public void ReturnArrow(){
        this.arrowX = 60;
        this.arrowXPoints[0] = arrowX;
        this.arrowXPoints[1] = arrowX + 15;
        this.arrowXPoints[2] = arrowX + 15;
        this.arrowXPoints[3] = arrowX + 30;
        this.arrowXPoints[4] = arrowX + 15;
        this.arrowXPoints[5] = arrowX + 15;
        this.arrowXPoints[6] = arrowX;
        UpdateArrowPolygon();
        arrowInFlight = false;
        repaint();
    }
    public void ReturnTargets(){
        this.firstTargetX = 300;
        this.firstTargetY = 175;
        this.secondTargetX = 400;
        this.secondTargetY = 175;
        repaint();
    }
    public void CheckCollision(){
        if(arrow.getBounds().intersects(firstTargetX - firstTargetRadius,
                firstTargetY - firstTargetRadius, 2 * firstTargetRadius, 2 * firstTargetRadius)){
            score += 1;
            ReturnArrow();
        }
        if(arrow.getBounds().intersects(secondTargetX - secondTargetRadius,
                secondTargetY - secondTargetRadius, 2 * secondTargetRadius, 2 * secondTargetRadius)){
            score += 2;
            ReturnArrow();
        }
    }
    public void ResetScore(){
        this.score = 0;
        this.shotsCount = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Счет игрока:",460,30);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(String.valueOf(score),460,50);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Выстрелы:",460,80);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(String.valueOf(shotsCount),460,100);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.fillOval(firstTargetX- firstTargetRadius, firstTargetY - firstTargetRadius, 2*firstTargetRadius, 
                2*firstTargetRadius);
        g2d.fillOval(secondTargetX- secondTargetRadius, secondTargetY - secondTargetRadius, 2*secondTargetRadius,
                2*secondTargetRadius);
        Line2D boardLine1 = new Line2D.Float(0, 350, 440, 350);
        Line2D boardLine2 = new Line2D.Float(440, 0, 440, 393);
        Line2D boardLine3 = new Line2D.Float(55,0,55,350);
        Line2D firstTargetLine = new Line2D.Float(firstTargetX,0,firstTargetX,350);
        Line2D secondTargetLine = new Line2D.Float(secondTargetX,0,secondTargetX,350);
        g2d.draw(boardLine1);
        g2d.draw(boardLine2);
        g2d.draw(boardLine3);
        g2d.draw(firstTargetLine);
        g2d.draw(secondTargetLine);
        g2d.setColor(Color.RED);
        g2d.fill(player);
        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(arrow);        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
