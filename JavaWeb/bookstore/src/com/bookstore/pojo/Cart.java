package com.bookstore.pojo;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName Cart
 * @Description 购物车JavaBean
 * @Author Josen
 * @Date 2020/6/12 13:39
 * @Version 1.0
 **/
public class Cart {
    // 购物车商品集合(LinkedHashMap用于遍历效率较高)
    private LinkedHashMap<Integer,CartItem> items = new LinkedHashMap<>();
    public Cart() {
    }

    /**
     * 添加一件商品到购物车
     * @param item
     * @return
     */
    public void addCartItem(CartItem item){
        int id = item.getId();
        CartItem cartItem = items.get(id);
        // 购物车未有该商品，添加新的元素到集合
        if(cartItem == null){
            items.put(id,item);
            return;
        }
        // 购物车已有该商品，更新数量和总金额
        cartItem.setCount(cartItem.getCount()+1);
        cartItem.setTotalPrice(cartItem.getTotalPrice().add(item.getTotalPrice()));
    }
    /**
     * 删除指定id的购物车商品
     * @param id
     * @return
     */
    public void deleteCartItemById(int id){
        items.remove(id);
    }
    /**
     * 清空购物车
     * @return
     */
    public void clearCart(){
        items.clear();
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
    // 商品总数量=items所有count相加
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            CartItem value = entry.getValue();
            totalCount += value.getCount();
        }
        return totalCount;
    }
    // 商品总金额=items所有totalPrice相加
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            CartItem value = entry.getValue();
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        System.out.println("totalPrice:"+totalPrice);

        return totalPrice;
    }

    public LinkedHashMap<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(LinkedHashMap<Integer, CartItem> items) {
        this.items = items;
    }
}
