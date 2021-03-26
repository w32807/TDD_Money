package currency;

// 1. 처음에 추상클래스로 만들자.
// 2. 추상클래스에서 공통화 하고자 하는 메소드를 추상 메소드로 선언 후 실제로 공통화 시키면서 추상화를 지운다.
public class Money implements Expression{
    // 자식에게까지 가능 (다른 패키지도 OK)
    protected int amount;
    protected String currency;
    
    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    @Override
    public boolean equals(Object obj) {
        Boolean result = false;
        
        if(obj instanceof Money) {
            Money money = (Money)obj;
            result = this.amount == money.amount && this.currency.equals(money.currency());
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "amount >> " + amount;
    }

    // 추상클래스는 인터페이스와는 다르게, 추상메소드와 일반 메소드도 같이 선언 가능하다.
    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    
    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }
    
    public String currency() {
        return currency;
    }
    
    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }
    
    public Expression plus(Money addend) {
        return new Sum(this, addend);
    }
    
    // instanceof 로 유효성검사를 하는 게 아닌, 인터페이스에 reduce 메소드를 선언해놓고
    // 여기로 와서 객체를 반환 할 수 있도록 함 (만약 Expression을 상속받은 다른 클래스가 있으면 해당 클래스를 반환)
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount/rate, to);
    }
    
    
    
}
