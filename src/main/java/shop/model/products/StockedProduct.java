package shop.model.products;

public class StockedProduct implements Product {

	private Type type;
	private String name;
	private int price;
	private int deliveryPeriod; // days to deliver

	public StockedProduct(String type, String name, double price, int deliveryPeriod) {
		this(Type.parseType(type), name, (int) (price * 100), deliveryPeriod);
	}

	private StockedProduct(Type type, String name, int price, int deliveryPeriod) {
		super();
		this.type = type;
		this.name = name;
		this.price = price;
		this.deliveryPeriod = deliveryPeriod;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getDeliveryPeriod() {
		return deliveryPeriod;
	}

	@Override
	public int getPrice() {
		return price;
	}

	private double toEuro() {
		return price / 100.0;
	}

	@Override
	public boolean isDigital() {
		return type == Type.DOWNLOAD;
	}

	@Override
	public boolean isPhysical() {
		return !this.isDigital();
	}

	@Override
	public String toString() {
		return (name.length() < 20 ? name : name.substring(0, 20) + "...") + ", (" + type.getName() + ", " + toEuro()
				+ "€)";
	}
}
