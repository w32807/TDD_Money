package currency;

public class Franc extends Money{
    
    // 생성자를 dollar와 통일 시켜, 부모 클래스의 생성자를 사용할 수 있도록 처리
    // 그러면서 public도 빠짐
    Franc(int amount, String currency) {
        super(amount, currency);
    }
}
