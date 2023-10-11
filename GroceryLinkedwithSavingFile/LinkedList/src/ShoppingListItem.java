public class ShoppingListItem {

    String itemName;
    String itemDescription;
    String purchaseDesc;
    double itemPrice;
    boolean purchased;

    ShoppingListItem next;
    
    ShoppingListItem (String itemName, String itemDescription, double itemPrice, String purchaseDesc){
    
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemPrice = itemPrice;
    this.purchaseDesc = purchaseDesc;
    this.purchased = false;
    this.next = null;

    }

   
    
}
