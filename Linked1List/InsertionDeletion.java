package LinkedList;
 
  class ShoppingListItem{
    
    String itemName;
    String itemDescription;
    double itemPrice;
    
    ShoppingListItem next;
    
    ShoppingListItem (String itemName, String itemDescription, double itemPrice){
      
      this.itemName = itemName;
      this.itemDescription = itemDescription;
      this.itemPrice = itemPrice;
      this.next = null;
    }
  }
  
  
  class ShoppingList{
    
    ShoppingListItem ulo;
    
    void addItem(String itemName, String itemDescription, double itemPrice){
      
      ShoppingListItem newList = new ShoppingListItem (itemName, itemDescription, itemPrice);
      
      if (ulo == null ) {
        ulo = newList;
      }
      
      else {
        
        ShoppingListItem current = ulo;
        while (current.next != null) {
          current = current.next;
        }
        
        current.next = ulo;
      }
    }
    
    
    void displayList() {
      
      if(ulo == null) {
        System.out.println("Your shopping list are empty");
        return;
      }
      
      ShoppingListItem current = ulo;
      
      System.out.println("--------YOUR SHOPPING LIST----------");
      
      while (current != null) {
        
        System.out.println("--------------------------------");
        System.out.println("Item Name: " + current.itemName);
        System.out.println("Item Description: " + current.itemDescription);
        System.out.println("It's Price: ₱ " + current.itemPrice);
        System.out.println("--------------------------------");
        
        current = current.next;
 
      }
    }
    
    
  }
  
  public class LinkedMain {
 
    public static void main(String[] args) {
 
      ShoppingList myGrocery = new ShoppingList();
      
      myGrocery.addItem("Jasmine Bigas", "Mahal na Bigas", 90.45);
      myGrocery.addItem("Sardinas", "Sardinas na may Mackarel sa loob", 40.56);
      myGrocery.addItem("Coke", "Coke Malake", 85);
      
      System.out.println("My Shopping List for One Week: ");
      myGrocery.displayList();
      
      
    }
  }
