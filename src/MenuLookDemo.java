
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

 
/* MenuLookDemo.java requires images/middle.gif. */
 
/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class MenuLookDemo{
    JTextArea output;
    JScrollPane scrollPane;
    static JFrame frame = new JFrame("Projectile Sim");
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int)screenSize.getWidth();
	static int height = (int)screenSize.getHeight();
	JToggleButton gravity = new JToggleButton("Gravity");
	JToggleButton drag = new JToggleButton("Drag");
	int m = 0;
	int d = 0;
	int g = 0;
	JFormattedTextField xPos;
	JFormattedTextField yPos;
	JFormattedTextField zPos;
	JFormattedTextField xVel;
	JFormattedTextField yVel;
	JFormattedTextField zVel;
	JFormattedTextField xAcc;
	JFormattedTextField yAcc;
	JFormattedTextField zAcc;
	JFormattedTextField massField;
	JFormattedTextField radiusField;
	JFormattedTextField dragcoField;
	JFormattedTextField xSpin;
	JFormattedTextField ySpin;
	JFormattedTextField zSpin;
	JFormattedTextField grav;
	JFormattedTextField densityField;
	JFormattedTextField viscosityField;
	JFormattedTextField xFlow;
	JFormattedTextField yFlow;
	JFormattedTextField zFlow;
	JFormattedTextField stepSizeField;
	JFormattedTextField stepsField;
	JToggleButton euler = new JToggleButton("Euler's");
	JToggleButton runge = new JToggleButton("RK4");
	JToggleButton floor = new JToggleButton();
	int fl = 0;
	int eu = 1;
	int ru = 0;
	
	
    public Container createIntroPane() {
    	// Setup style
        SimpleAttributeSet title = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(title, true);
    	StyleConstants.setFontSize(title, 20);
    	
    	SimpleAttributeSet body = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(body , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(body, 12);
    	
        //Create intro pane
        JPanel introPane = new JPanel(new BorderLayout());
        introPane.setOpaque(true);
        
        // Create a title
        JTextPane header = new JTextPane();
        header.setParagraphAttributes(title,true);
        header.setEditable(false);
        header.setText("Projectile Simulator");
        introPane.add(header, BorderLayout.NORTH);
        
        // Create a body
        JTextPane text = new JTextPane();
        text.setParagraphAttributes(body,true);
        text.setText("A program for calculating the trajectories of projectiles, created by Arnas Cibulskis & Kieran Keegan");
        introPane.add(text, BorderLayout.CENTER);
        
        // Create continue button
        JButton test = new JButton();
        test.setText("Continue");
        test.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	 createAndShowGUI(2);
          }
        });
        introPane.add(test, BorderLayout.PAGE_END);

        return introPane;
    }

    public Container createObjectPane(){
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
    	DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
    	decimalFormat.setGroupingUsed(false);
    	
    	Insets five = new Insets(5, 5, 5, 5);
    	Insets zero = new Insets(0, 0, 0, 0);
    	
        SimpleAttributeSet title = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(title, true);
    	StyleConstants.setFontSize(title, 20);
    	
    	SimpleAttributeSet subtitle = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(subtitle , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(subtitle, true);
    	StyleConstants.setFontSize(subtitle, 15);
    	
    	SimpleAttributeSet body = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(body , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(body, 12);
    	
    	SimpleAttributeSet forces = new SimpleAttributeSet(); 
    	StyleConstants.setAlignment(forces , StyleConstants.ALIGN_LEFT);
    	
    	GridBagConstraints c = new GridBagConstraints();
    	// Object pane
    	JPanel objectPane = new JPanel(new GridBagLayout());
    	objectPane.setOpaque(true);
    	objectPane.setBorder(BorderFactory.createLineBorder(Color.black));
    	objectPane.setSize(125, 400);
    	// Title
    	JTextPane objectHeader = new JTextPane();
    	objectHeader.setText("Object");
    	objectHeader.setParagraphAttributes(title, true);
    	c.gridwidth = 3;
        c.gridy = 0;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        objectPane.add(objectHeader, c);
        c.ipady = 0;
        c.ipadx = 0;
        
        // Position
    	JTextPane positionHeader = new JTextPane();
    	positionHeader.setText("Pos(m)");
    	positionHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 1;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(positionHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        JLabel positionLabel = new JLabel("Position vector");
        //Vector3 position = new Vector3(2, -3, 6);
        xPos = new JFormattedTextField();
        xPos.setColumns(2);
        yPos = new JFormattedTextField();
        yPos.setColumns(2);
        zPos = new JFormattedTextField();
        zPos.setColumns(2);
       
        c.gridwidth = 1;
        xPos.setValue(new Float(2));
        xPos.setToolTipText("i vector");
        c.gridy = 3;
        c.gridx = 0;
        objectPane.add(xPos, c);

        yPos.setValue(new Float(-3));
        yPos.setToolTipText("j vector");
        c.gridx = 1;
        c.insets = new Insets(0, 2 ,0, 0);
        objectPane.add(yPos ,c);
        c.ipadx = 0;
        c.insets = zero;
    	
        zPos.setValue(new Float(6));
        zPos.setToolTipText("k vector");
        c.gridx = 2;
        c.insets = new Insets(0, 3 ,0, 0);
        objectPane.add(zPos ,c);
        c.insets = zero;
        
        // Velocity
    	JTextPane velocityHeader = new JTextPane();
    	velocityHeader.setText("Vel(m)");
    	velocityHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(velocityHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        //Vector3 velocity = new Vector3(-5, 14, 2);
        xVel = new JFormattedTextField();
        xVel.setColumns(2);
        xVel.setToolTipText("i vector");
        yVel = new JFormattedTextField();
        yVel.setColumns(2);
        yVel.setToolTipText("j vector");
        zVel = new JFormattedTextField();
        zVel.setColumns(2);
        zVel.setToolTipText("k vector");
        c.gridwidth = 1;
        
        xVel.setValue(new Float(-5));
        c.gridy = 5;
        c.gridx = 0;
        objectPane.add(xVel, c);

        yVel.setValue(new Float(14));
        c.gridx = 1;
        c.insets = new Insets(0, 2 ,0, 0);
        objectPane.add(yVel ,c);
        c.ipadx = 0;
        c.insets = zero;
    	
        zVel.setValue(new Float(2));
        c.gridx = 2;
        c.insets = new Insets(0, 3 ,0, 0);
        objectPane.add(zVel ,c);
        c.insets = zero;
        
        // Acceleration
        JTextPane accelerationHeader = new JTextPane();
        accelerationHeader.setText("Acc(m)");
        accelerationHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(accelerationHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        
        xAcc = new JFormattedTextField(decimalFormat);
        xAcc.setColumns(2);
        xAcc.setToolTipText("i vector");
        
        yAcc = new JFormattedTextField(decimalFormat);
        yAcc.setColumns(2);
        yAcc.setToolTipText("j vector");
        
        zAcc = new JFormattedTextField(decimalFormat);
        zAcc.setColumns(2);
        zAcc.setToolTipText("k vector");
        
        c.gridwidth = 1;
        xAcc.setValue(new Float(0));
        c.gridy = 7;
        c.gridx = 0;
        objectPane.add(xAcc, c);

        yAcc.setValue(new Float(0));
        c.gridx = 1;
        c.insets = new Insets(0, 2 ,0, 0);
        objectPane.add(yAcc ,c);
        c.ipadx = 0;
        c.insets = zero;
    	
        zAcc.setValue(new Float(0));
        c.gridx = 2;
        c.insets = new Insets(0, 3 ,0, 0);
        objectPane.add(zAcc ,c);
        c.insets = zero;
        // Mass
        JTextPane massHeader = new JTextPane();
        massHeader.setText("Mass(kg)");
        massHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 8;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(massHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        massField = new JFormattedTextField(decimalFormat);
        massField.setColumns(5);
        massField.setToolTipText("Mass of the object");
        c.gridwidth = 3;
        massField.setValue(new Float(4.08));
        c.gridy = 9;
        c.gridx = 0;
        objectPane.add(massField, c);
        
        // Radius
        JTextPane radiusHeader = new JTextPane();
        radiusHeader.setText("Radius(m)");
        radiusHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 10;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(radiusHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        radiusField = new JFormattedTextField(decimalFormat);
        radiusField.setColumns(5);
        radiusField.setToolTipText("Radius of the object");
        c.gridwidth = 3;
        radiusField.setValue(new Float(0.05));
        c.gridy = 11;
        c.gridx = 0;
        objectPane.add(radiusField, c);
        
        // Drag Coefficient
        JTextPane dragcoHeader = new JTextPane();
        dragcoHeader.setText("Drag Co");
        dragcoHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 12;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(dragcoHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        // Buttons
        dragcoField = new JFormattedTextField(decimalFormat);
        dragcoField.setColumns(5);
        dragcoField.setToolTipText("Drag coefficient of the object");
        c.gridwidth = 3;
        dragcoField.setValue(new Float(0.1));
        c.gridy = 13;
        c.gridx = 0;
        objectPane.add(dragcoField, c);
        
        // Spin
    	JTextPane spinHeader = new JTextPane();
    	spinHeader.setText("Spin(m)");
    	spinHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 14;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(spinHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        
        // Buttons 
        xSpin = new JFormattedTextField();
        xSpin.setColumns(2);
        ySpin = new JFormattedTextField();
        ySpin.setColumns(2);
        zSpin = new JFormattedTextField();
        zSpin.setColumns(2);
        
        c.gridwidth = 1;
        xSpin.setValue(new Float(-3.33333));
        xSpin.setToolTipText("i vector");
        c.gridy = 15;
        c.gridx = 0;
        objectPane.add(xSpin, c);

        ySpin.setValue(new Float(-1.66666));
        ySpin.setToolTipText("j vector");
        c.gridx = 1;
        c.insets = new Insets(0, 2 ,0, 0);
        objectPane.add(ySpin ,c);
        c.ipadx = 0;
        c.insets = zero;
    	
        zSpin.setValue(new Float(3.33333));
        zSpin.setToolTipText("k vector");
        c.gridx = 2;
        c.insets = new Insets(0, 3 ,0, 0);
        objectPane.add(zSpin ,c);
        c.insets = zero;
        
        // Gravity
    	JTextPane gravityHeader = new JTextPane();
    	gravityHeader.setText("g(ms²)");
    	gravityHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 16;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        objectPane.add(gravityHeader, c);
        c.insets = zero;
        c.gridwidth = 1;
        c.ipady = 0;
        c.ipadx = 0;
        
        // Buttons 
        grav = new JFormattedTextField();
        grav.setColumns(2);
        c.gridwidth = 3;
        grav.setValue(new Float(9.81));
        grav.setToolTipText("Acceleration due to gravity");
        grav.addPropertyChangeListener(new PropertyChangeListener()
        {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue() != null){
					gravity.setSelected(true);
					g = 1;
				}
			
			}
        });
        c.gridy = 17;
        c.gridx = 0;
        objectPane.add(grav, c);
        
    	return objectPane;
    }
    
    public Container createFluidPane() {
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
    	DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
    	decimalFormat.setGroupingUsed(false);
    	
    	Insets five = new Insets(5, 5, 5, 5);
    	Insets zero = new Insets(0, 0, 0, 0);
    	
        SimpleAttributeSet title = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(title, true);
    	StyleConstants.setFontSize(title, 20);
    	
    	SimpleAttributeSet subtitle = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(subtitle , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(subtitle, true);
    	StyleConstants.setFontSize(subtitle, 15);
    	
    	SimpleAttributeSet body = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(body , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(body, 12);
    	
    	SimpleAttributeSet forces = new SimpleAttributeSet(); 
    	StyleConstants.setAlignment(forces , StyleConstants.ALIGN_LEFT);
    	
    	GridBagConstraints c = new GridBagConstraints();
    	
    	// Fluid pane
    	JPanel fluidPane = new JPanel(new GridBagLayout());
    	fluidPane.setOpaque(true);
    	fluidPane.setBorder(BorderFactory.createLineBorder(Color.black));
    	fluidPane.setSize(125, 400);
    	// Title
    	JTextPane fluidHeader = new JTextPane();
    	fluidHeader.setText("Fluid");
    	fluidHeader.setParagraphAttributes(title, true);
    	c.gridwidth = 3;
        c.gridy = 0;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        fluidPane.add(fluidHeader, c);
        c.ipady = 0;
        c.ipadx = 0;
        
        // Density
    	JTextPane densityHeader = new JTextPane();
    	densityHeader.setText("Density");
    	densityHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 1;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        fluidPane.add(densityHeader, c);
        c.insets = zero;
        c.ipady = 0;
        c.ipadx = 0;
        
        // Button
        densityField = new JFormattedTextField(decimalFormat);
        densityField.setColumns(2);
        densityField.setValue(new Float(80));
        densityField.setToolTipText("Density");
        c.gridy = 3;
        c.gridx = 0;
        fluidPane.add(densityField, c);
        
        // Viscosity
    	JTextPane viscosityHeader = new JTextPane();
    	viscosityHeader.setText("Visc");
    	viscosityHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 4;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        fluidPane.add(viscosityHeader, c);
        c.insets = zero;
        c.ipady = 0;
        c.ipadx = 0;
        
        // Button
        viscosityField = new JFormattedTextField(decimalFormat);
        viscosityField.setColumns(2);
        viscosityField.setValue(new Float(1));
        viscosityField.setToolTipText("Viscosity");
        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 3;
        fluidPane.add(viscosityField, c);
        
        // Flow
    	JTextPane flowHeader = new JTextPane();
    	flowHeader.setText("Flow");
    	flowHeader.setParagraphAttributes(subtitle, true);
    	
        c.gridy = 6;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 5;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        fluidPane.add(flowHeader, c);
        c.insets = zero;
        c.ipady = 0;
        c.ipadx = 0;
        
        // Buttons 
        xFlow = new JFormattedTextField(decimalFormat);
        xFlow.setColumns(2);
        yFlow = new JFormattedTextField(decimalFormat);
        yFlow.setColumns(2);
        zFlow = new JFormattedTextField(decimalFormat);
        zFlow.setColumns(2);
        
        c.gridwidth = 1;
        xFlow.setValue(new Float(2));
        xFlow.setToolTipText("i vector");
        c.gridy = 7;
        c.gridx = 0;
        xFlow.addPropertyChangeListener(new PropertyChangeListener()
        {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue() != null){
					drag.setSelected(true);
					d = 1;
				}
			
			}
        });
        fluidPane.add(xFlow, c);

        yFlow.setValue(new Float(3));
        yFlow.setToolTipText("j vector");
        c.gridx = 1;
        c.insets = new Insets(0, 2 ,0, 0);
        yFlow.addPropertyChangeListener(new PropertyChangeListener()
        {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue() != null){
					drag.setSelected(true);
					d = 1;
				}
			
			}
        });
        fluidPane.add(yFlow ,c);
        c.insets = zero;
    	
        zFlow.setValue(new Float(0));
        zFlow.setToolTipText("k vector");
        c.gridx = 2;
        c.insets = new Insets(0, 3 ,0, 0);
        zFlow.addPropertyChangeListener(new PropertyChangeListener()
        {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue() != null){
					drag.setSelected(true);
					d = 1;
				}
			
			}
        });
        fluidPane.add(zFlow ,c);
        c.insets = zero;
        
        return fluidPane;
	}
    
    public Container createMenuPane() {
    	// Setup style
    	Insets five = new Insets(5, 5, 5, 5);
    	Insets zero = new Insets(0, 0, 0, 0);
    	
        SimpleAttributeSet title = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(title, true);
    	StyleConstants.setFontSize(title, 20);
    	
    	SimpleAttributeSet subtitle = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(subtitle , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(subtitle, true);
    	StyleConstants.setFontSize(subtitle, 15);
    	
    	SimpleAttributeSet body = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(body , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(body, 12);
    	
    	SimpleAttributeSet forces = new SimpleAttributeSet(); 
    	StyleConstants.setAlignment(forces , StyleConstants.ALIGN_LEFT);
    	
    	GridBagConstraints c = new GridBagConstraints();
    	
        // Create menu pane
        JPanel menuPane = new JPanel(new GridBagLayout());
        menuPane.setOpaque(true);
        
        

        // Forces Pane
        JPanel forcesPane = new JPanel(new GridBagLayout());
        forcesPane.setBorder(BorderFactory.createLineBorder(Color.black));
        forcesPane.setSize(125, 400);
        // Title
        JTextPane forcesHeader = new JTextPane();
        forcesHeader.setText("Forces");
        forcesHeader.setParagraphAttributes(title, true);
        c.gridy = 0;
        c.ipady = 5;
        c.ipadx = 5;
        forcesPane.add(forcesHeader, c);
        c.ipady = 0;
        c.ipadx = 0;
        
        // Buttons
        
        gravity.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        		if(g == 0){
        			g = 1;
        		}
        		else{
        			g = 0;
        		}
        	}
        });
        gravity.setSize(100, 100);
        JToggleButton magnus = new JToggleButton("Magnus");
        magnus.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		if(m == 0){
        			m = 1;
        		}
        		else{
        			m = 0;
        		}
        		
        	}
        });
        magnus.setSize(100, 100);
        
        drag.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		if(d == 0){
        			d = 1;
        		}
        		else{
        			d = 0;
        		}
        		
        	}
        });
        drag.setSize(100, 100);
        
        
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        forcesPane.add(gravity, c);
        c.gridy = 2;
        forcesPane.add(drag, c);
        c.gridy = 3;
        forcesPane.add(magnus, c);

        // End Forces Pane
 
      
        
        c.insets = zero;
        c.gridheight = 0;
        
        c.gridwidth = 1;
       	
       
        c.gridy = 2;
        c.insets = new Insets(30, 20, 0, 20);
       	c.anchor = GridBagConstraints.NORTH;
        menuPane.add(forcesPane, c);
        c.gridx = 2;
        menuPane.add(createObjectPane(), c);
        c.gridx = 3;
        menuPane.add(createFluidPane(), c);
        c.gridx = 4;
        menuPane.add(createSettingsPane(), c);
        
        c.anchor = GridBagConstraints.SOUTH;
        c.gridwidth = 3;
        c.gridy = 3;
        c.gridx = 1;
        return menuPane;
    }
    
    public Container createSettingsPane(){
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
    	DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
    	decimalFormat.setGroupingUsed(false);
    	
    	Insets zero = new Insets(0, 0, 0, 0);
    	
        SimpleAttributeSet title = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(title, true);
    	StyleConstants.setFontSize(title, 20);
    	
    	SimpleAttributeSet subtitle = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(subtitle , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setBold(subtitle, true);
    	StyleConstants.setFontSize(subtitle, 15);
    	
    	SimpleAttributeSet body = new SimpleAttributeSet();  
    	StyleConstants.setAlignment(body , StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(body, 12);
    	
    	SimpleAttributeSet inputs = new SimpleAttributeSet(); 
    	StyleConstants.setAlignment(inputs , StyleConstants.ALIGN_LEFT);
    	
    	GridBagConstraints c = new GridBagConstraints();
    	// Settings pane
    	JPanel settingsPane = new JPanel(new GridBagLayout());
     	settingsPane.setOpaque(true);
    	 settingsPane.setBorder(BorderFactory.createLineBorder(Color.black));
    	 settingsPane.setSize(125, 400);
    	 // Title
    	 JTextPane settingsHeader = new JTextPane();
    	 settingsHeader.setText("Options");
    	 settingsHeader.setParagraphAttributes(title, true);
    	 c.gridy = 0;
         c.ipady = 5;
         c.ipadx = 5;
         c.gridx = 0;
         c.gridwidth = 2;
         settingsPane.add(settingsHeader, c);
         c.ipady = 0;
         c.ipadx = 0;
         
         JTextPane methodHeader = new JTextPane();
         methodHeader.setText("Method");
         methodHeader.setParagraphAttributes(subtitle, true);
    	 c.gridy = 1;
    	 c.gridwidth = 3;
    	 c.insets = new Insets(10, 0, 0, 0);
    	 settingsPane.add(methodHeader, c);
    	 c.insets = zero;
    	 
         euler.doClick();
         euler.addActionListener(new ActionListener()
         {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        		if(eu == 0){
        			eu = 1;
        			ru = 0;
        			runge.setSelected(false);
        		}
           		else{
           			euler.setSelected(true);
        		}
        	}
         });
         euler.setSize(100, 100);
         c.gridwidth = 2;
         c.gridy = 2;
         c.gridx = 1;
         settingsPane.add(euler, c);
         
         runge.addActionListener(new ActionListener()
         {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        		if(ru == 0){
        			ru = 1;
        			eu = 0;
        			euler.setSelected(false);
        		}
        		else{
        			runge.setSelected(true);
        		}
        	}
         });
         runge.setSize(100, 100);
         c.gridwidth = 2;
         c.gridy = 3;
         c.gridx = 1;
         settingsPane.add(runge, c);
         
         c.gridy = 4;
         c.gridwidth = 3;
         c.insets = new Insets(10, 0, 0, 0);
         
         // Step Size
     	JTextPane stepsizeHeader = new JTextPane();
     	stepsizeHeader.setText("Step Size");
     	stepsizeHeader.setParagraphAttributes(subtitle, true);
     	
         c.gridy = 5;
         c.gridx = 0;
         c.ipady = 5;
         c.ipadx = 5;
         c.gridwidth = 3;
         c.insets = new Insets(10, 0, 0, 0);
         settingsPane.add(stepsizeHeader, c);
         c.insets = zero;
         c.ipady = 0;
         c.ipadx = 0;
         
         // Button
         stepSizeField = new JFormattedTextField(decimalFormat);
         stepSizeField.setColumns(2);
         stepSizeField.setValue(new Float(0.1f));
         stepSizeField.setToolTipText("Increase time by this amount each iteration, in seconds");
         c.gridy = 6;
         c.gridx = 0;
         settingsPane.add(stepSizeField, c);
         
         // Iterations
      	JTextPane iterationsHeader = new JTextPane();
      	iterationsHeader.setText("Steps");
      	iterationsHeader.setParagraphAttributes(subtitle, true);
      	
          c.gridy = 7;
          c.gridx = 0;
          c.ipady = 5;
          c.ipadx = 5;
          c.gridwidth = 3;
          c.insets = new Insets(10, 0, 0, 0);
          settingsPane.add(iterationsHeader, c);
          c.insets = zero;
          c.ipady = 0;
          c.ipadx = 0;
          
          // Button
          stepsField = new JFormattedTextField(decimalFormat);
          stepsField.setColumns(2);
          stepsField.setValue(new Float(20));
          stepsField.setToolTipText("Amount of iterations");
          c.gridy = 8;
          c.gridx = 0;
          settingsPane.add(stepsField, c);
          
          c.insets = new Insets(10, 0, 0, 0);
          c.gridy = 9;

          
 
          floor.setLayout(new BorderLayout());
          JLabel label1 = new JLabel("  Run until");
          label1.setSize(100,100);
          JLabel label2 = new JLabel("hits ground");
          label2.setSize(100,100);
          floor.add(BorderLayout.NORTH,label1);
          floor.add(BorderLayout.SOUTH,label2);
          floor.setMargin(new Insets(5,5,5,5));
          
          floor.setToolTipText("Toggle for the simulation to stop when the projectile hits the ground");
          floor.addActionListener(new ActionListener()
          {
         	public void actionPerformed(ActionEvent e)
         	{
         		
         		if(fl == 0){
         			fl = 1;
         		}
         		else{
         			fl = 0;
         		}
         		System.out.println(fl);
         	}
          });
          settingsPane.add(floor, c);
          
         return settingsPane;
    }
    
    public Container createMainPane(){
    	SimpleAttributeSet title = new SimpleAttributeSet();  
       	StyleConstants.setAlignment(title , StyleConstants.ALIGN_CENTER);
       	StyleConstants.setBold(title, true);
       	StyleConstants.setFontSize(title, 20);
       	
    	
    	JPanel mainPane = new JPanel();
    	JPanel goPane = new JPanel(new GridBagLayout());
        JButton go = new JButton("Calculate!");
        go.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	  if(g == 0 && d == 0 && m == 0){
        		  
        	  }
        	  else{
        		  createAndShowGUI(3);
        	  }
          }
        });
        goPane.add(go);
        
        // Create a title
        JTextPane header = new JTextPane();
        header.setParagraphAttributes(title,true);
        header.setEditable(false);
        header.setText("Projectile Simulator");
        
        mainPane.add(header);
        mainPane.add(createMenuPane());
        mainPane.add(goPane);
        return mainPane;
    }
    
    public Container createCalculationPane(Vector3 pos, Vector3 vel, Vector3 acc, Float massF, Float radiusF, Float dragcoF, Vector3 spin, Float gravF, Float densityF, Float viscosityF, Vector3 flow, int gr, int dr, int ma, int method, float stepSize, int steps, int ground){
    	JPanel calculationMenu = new JPanel();
    	calculationMenu.setSize(600,100);
    	JTextArea calculationText = new JTextArea(21,50);
    	calculationText.setEditable(true);
    	calculationText.setLineWrap(true);
    	calculationText.setVisible(true);
    	calculationText.setSize(570, 100);
    	calculationText.setWrapStyleWord(true);
    	
    	
    	/**
    	 * 
    	 * float mass, float radius, float dragCoefficient, Vector3 position, 
    	 * Vector3 velocity, Vector3 acceleration, Vector3 spin,
    	 * float densityF, float viscosity, Vector3 flow, float 
    	 * grav, int gr, int dr, int ma)
    	 * 
    	 */
		// 
    	Setup setup = new Setup();
    	String output = setup.start(massF, radiusF, dragcoF, pos, vel, acc, spin, densityF, viscosityF, flow, gravF, gr, dr, ma, method, stepSize, steps, ground);
    	
    	calculationText.append(output);
    	
    	
    	calculationMenu.add(calculationText, BorderLayout.NORTH);
    	JScrollPane scroll = new JScrollPane (calculationText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	calculationMenu.add(scroll, true);
    	
    	calculationMenu.setVisible(true);

    	return calculationMenu;
    }

    
	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MenuLookDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    
    private void createAndShowGUI(int i) {
    	
    	
        //Create and set up the content pane.
        MenuLookDemo demo = new MenuLookDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(i == 1){
        	frame.setContentPane(demo.createIntroPane());
        	frame.setSize(450, 260);
        	frame.setLocation(new Point(width/2 - 225, height - height + 10));
        	frame.setResizable(false);
        }
        else if(i == 2){
        	frame.setContentPane(demo.createMainPane());
        	frame.setSize(600, 700);
        	frame.setLocation(new Point(width/2 - 300, height - height + 10));
        	frame.setResizable(false);
        }
        else if(i == 3){
        	Float xPosF = Float.parseFloat(xPos.getText());
        	Float yPosF = Float.parseFloat(yPos.getText());
        	Float zPosF = Float.parseFloat(zPos.getText());
        	Vector3 pos = new Vector3(xPosF, yPosF, zPosF);
        	
        	Float xVelF = Float.parseFloat(xVel.getText());
        	Float yVelF = Float.parseFloat(yVel.getText());
        	Float zVelF = Float.parseFloat(zVel.getText());
        	Vector3 vel = new Vector3(xVelF, yVelF, zVelF);
        	
        	Float xAccF = Float.parseFloat(xAcc.getText());
        	Float yAccF = Float.parseFloat(yAcc.getText());
        	Float zAccF = Float.parseFloat(zAcc.getText());
        	Vector3 acc = new Vector3(xAccF, yAccF, zAccF);
        	
        	Float massF = Float.parseFloat(massField.getText());
        	Float radiusF = Float.parseFloat(radiusField.getText());
        	Float dragcoF = Float.parseFloat(dragcoField.getText());
        	
        	Float xSpinF = Float.parseFloat(xSpin.getText());
        	Float ySpinF = Float.parseFloat(ySpin.getText());
        	Float zSpinF = Float.parseFloat(zSpin.getText());
        	Vector3 spin = new Vector3(xSpinF, ySpinF, zSpinF);
        	
        	Float gravF = Float.parseFloat(grav.getText());
        	
        	Float densityF = Float.parseFloat(densityField.getText());
        	Float viscosityF = Float.parseFloat(viscosityField.getText());
        	
        	Float xFlowF = Float.parseFloat(xFlow.getText());
        	Float yFlowF = Float.parseFloat(yFlow.getText());
        	Float zFlowF = Float.parseFloat(zFlow.getText());
        	Vector3 flow = new Vector3(xFlowF, yFlowF, zFlowF);
        	
        	float stepSize = Float.parseFloat(stepSizeField.getText());
        	int steps = Integer.parseInt(stepsField.getText());
        	
        	int method;
        	if (eu == 1){
        		method = 1;
        	}
        	else{
        		method = 2;
        	}
        	
        	int gr = g;
        	int dr = d;
        	int ma = m;
        	
        	int ground = fl;
        	
        	frame.setContentPane(demo.createCalculationPane(pos, vel, acc, massF, radiusF, dragcoF, spin, gravF, densityF, viscosityF, flow, gr, dr, ma, method, stepSize, steps, ground));
        	frame.setSize(600, 400);
        	frame.setLocation(new Point(width/2 - 300, height - height + 10));
        }
        frame.setVisible(true);
    }

	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MenuLookDemo theApp = new MenuLookDemo();
            	theApp.createAndShowGUI(1);
            }
        });
    }
}