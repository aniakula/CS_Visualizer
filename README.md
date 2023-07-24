# CS_Visualizer
Interactive model builder for graph theory, binary trees, and programming flow-charts

# Setup:
- Download Java Runtime Environment (JRE): https://www.java.com/en/download/manual.jsp
- Download desired version of CS_Vision (download the .jar file found in each release)
- Once download is complete, navigate to the .jar file for CS_Vision from your terminal and run the following command:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/aee44a90-2a78-4a26-804e-6fa3a003f7ac)
- Refer to version history and user guide
  
# How to use:
CS_Vision in an interactive model building application for computer science concepts (Binary Trees, Graph Theory, Digital Electronics, and Programming Flowcharts). Each panel uses Draggable nodes, and connecting lines to create various structures, and perform calculations with these structures. There are four workspaces included in the CS_Vision application, each involvedin one of the CS concepts described above. The rest of this guide will explore all options that are available in each workspace of the application. Each workspace contains main menu options and Node options, both of which will be described below in detail.

## Binary Tree Panel:
this is the panel that is opened on launch of the program. The actions menu in this panel has the following options: 

![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/2aa6daac-71f7-49b7-b133-8fd05fd1d3f1)
### Options:
- the first three options will allow the user to navigate to the other panels
- the help option will open a window which outlines how to use the Binary Tree Panel
- the Add new node option will allow the user to generate a new node onto the screen. After selecting the option, the user will be prompted to enter a name for the new node, and a Binary Tree node is generated once a name is selected. ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/52d27b7b-d80c-45be-bf2d-013884d34a68)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/d85a4a30-805a-4a02-868d-b0fbe0402cbe)
- the Change display option will allow the user to select a new display type for the nodes. There are three displays: name, value, and level. A node with the name display will display its name, a node with value display will display the set value of a node (default is 0), a node with the level display will display its depth within the tree(the root is 0, its children are 1, etc.) ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/718d2137-2bd8-4b1f-8a72-0b583dfef90a)
  ### names display:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/3f7a1490-59de-45ae-a6a4-5950870de767)
  ### values display:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/2904840b-9eeb-4af5-8075-ca5ee378f173)
  ### levels display:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/e06794ab-0487-495f-84dd-a770a73cbe3f)

- the Organize Binary Tree option will organize the tree in decreasing-depth order. This option is helpful if the nodes in the tree have been misplaced by dragging ex:
  ### before selecting organize:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/f53fc294-4a06-4c44-9248-d6b8d0d7ef79)

  ### after selecting organize:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/38feebc7-8aef-49b5-97e4-a9f051625c51)
- the clear workspace option deletes all nodes and connections in the current workspace.
  
## Binary Tree panel Node options:
- these options can be activated with a right click on any Node in the workspace, below is the Node menu in the binary tree panel:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/0ec023a1-05c6-4874-8b08-ff4620a052af)
  
- the rename option allows the user to change the name of the selected node
  
- the color option allows the user to change the fill color of the selected node ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/e99f3ce1-b8f2-4d88-95bf-2ea657053bbc)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/8b9d202c-68c3-4fe8-9551-0d3645adfab5)
  
- the Add Child Node option adds a new node as a child to the selected node (each node may only have two children) ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/f4c86b66-8971-4efd-874c-6b05f73d4f1d)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/673043b8-f986-44c7-9eac-59e436955684)
  
- the Delete connection option is under construction (as of version 1.0)
  
- The Delete Node option deletes the selected node, and all connections between it, and other nodes ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/8e2f4fef-d22a-44c7-95f5-df14e0f1628a)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/b1cd40e3-a1e0-40a8-8934-11eb94755aa5)
  
- The Select Value option will allow the user to set the value of the selected node ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/cbf4e026-f6e0-4d00-bfaa-e8046dcfc258)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/ee62e2a4-ba50-40a1-ae59-f36bd2ed1a89)
  
## Flow Chart Panel:
- under construction (as of version 1.0)

## Graph Panel:
This Panel can be navigated to through the "Graph" workspace option in the other panels. The actions menu in this panel has the following options: 

![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/9f3c3e28-da05-4d9f-b9f0-e90788f6f8bc)

## Options:
- the first three options will allow the user to navigate to the other panels
  
- the help option will open a window which outlines how to use the Graph Panel
  
- the Add new node option will allow the user to generate a new node onto the screen. After selecting the option, the user will be prompted to enter a name for the new node, and a Graph node is generated once a name is selected.
  
- the make Graph non-directional option changes any directional edges in the graph to un-directed edges ex:
  
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/873268b5-1f89-4981-9991-4ccebf10fc62)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/cf5deec9-ff12-4991-8b2f-6710daf1ca37)

- the clear workspace option deletes all nodes and edges in the current workspace.

## Graph Panel Node Options:
- these options can be activated with a right click on any Node in the workspace, below is the Node menu in the graph panel:
  
![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/16b876d1-1646-44a4-8f29-2fd62718c4b9)

- the rename option allows the user to change the name of the selected node
  
- the color option allows the user to change the fill color of the selected node

- the Add Node option allows the user to add a new graph node connected to the selected graph node. The user is prompted to select a name and edge directionality for the new node. Once both of these choices are made, a new graph node will be generated ex:

  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/31022810-9385-48b7-b6a4-00697326b0e7)
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/e03ca64a-1106-4fa1-ab8a-a857fd002271)


### non-directed edge:
![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/9b8cbdbe-d8b6-4840-a189-8d6dc8102f0c)

### directed towards new node:
![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/3313b86f-4744-4868-8aa4-304551749161)

### directed away from new node:
![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/757d9552-a49c-423a-bbeb-55bd55aa1ccc)

- the Delete connection option is under construction (as of version 1.0)

- the Add Directed Edge option adss an edge between the selected node, directed to the next node that is selected ex:

  ### before selecting the Add Directed Edge option:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/29dd32bf-7c24-40de-a926-502324813ac0)
  ### after selecting the Add Directed Edge option on Node 2:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/87bff801-cdd4-45e6-90f4-e800ff26fad6)
  ### after selecting Graph Node as the node to draw an edge to from Node 2:
  ![image](https://github.com/Anidragon/CS_Visualizer/assets/81329162/cad02d03-fff5-4e1f-b379-aba972fcc062)


- The Delete Node option deletes the selected node, and all connections between it, and other nodes

- 
