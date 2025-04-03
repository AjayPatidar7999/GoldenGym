package com.aithinkers.GoldenGym.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    private int quantity;

    public CartItem() {}
    public CartItem(Supplement supplement, int quantity) {
        this.supplement = supplement;
        this.quantity = quantity;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Supplement getSupplement() {
		return supplement;
	}
	public void setSupplement(Supplement supplement) {
		this.supplement = supplement;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", supplement=" + supplement + ", quantity=" + quantity + "]";
	}

    // Getters and Setters
    
}
