import java.util.ArrayList;
import java.util.Random;


public class TicketOrder {
    static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static void main(String[] args) {

        assert new ComplimentaryTicket().equals("SN: 400, $0");
        assert new WalkupTicket().equals("SN: 561, $50");
        assert new AdvanceTicket(11).equals("SN: SN: 573, $30");
        assert new AdvanceTicket(3).equals("SN: SN: 573, $50");
        assert new StudentAdvanceTicket(11).equals("SN: SN: 573, $15");
        assert new StudentAdvanceTicket(3).equals("SN: SN: 573, $25");

        TicketOrder to = new TicketOrder();
        System.out.print(to.toString());
        to.add(new ComplimentaryTicket());
        System.out.print(to.toString());
        to.add(new WalkupTicket());
        System.out.print(to.toString());
        to.add(new AdvanceTicket(20));
        to.add(new AdvanceTicket(1));
        System.out.print(to.toString());
        to.add(new StudentAdvanceTicket(20));
        to.add(new StudentAdvanceTicket(1));
        System.out.print(to.toString());


    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Price: $");
        sb.append(totalPrice());
        sb.append("\n");
        sb.append("Tickets:\n");
        for (Ticket ticket : tickets) {
            sb.append(ticket.toString());
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    public int totalPrice() {
        int price = 0;
        for (Ticket ticket : tickets) {
            price += ticket.getPrice();
        }
        return price;
    }
}




abstract class Ticket {
    static final int MAX_TICKET_NUMBER = 999;
    static final Random random = new Random();

    static ArrayList<Integer> usedSerialNumbers = new ArrayList<Integer>();

    private Integer serialNumber;

    public Ticket() {
        while (serialNumber == null || usedSerialNumbers.contains(serialNumber)){
            serialNumber = generateSerialNumber();
        }
        usedSerialNumbers.add(serialNumber);
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "SN: " + serialNumber + ", $" + getPrice();
    }

    public abstract Integer getPrice();


    private Integer generateSerialNumber() {
        return random.nextInt(MAX_TICKET_NUMBER);
    }
}

abstract class FixedPriceTicket extends Ticket {

    private int price;

    public FixedPriceTicket(Integer price) {
        this.price = price;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}

class ComplimentaryTicket extends FixedPriceTicket {
    public ComplimentaryTicket() {
        super(0);
    }
}


class WalkupTicket extends FixedPriceTicket {
    public WalkupTicket() {
        super(50);
    }
}

class AdvanceTicket extends Ticket {

    private static final int EARLY_BIRD_MIN_DAYS_IN_ADVANCE = 10;
    private static final int EARLY_BIRD_PRICE = 30;
    private static final int LATE_PRICE = 40;

    private int daysInAdvance;

    public AdvanceTicket(Integer daysInAdvance) {
        this.daysInAdvance = daysInAdvance;
    }

    @Override
    public Integer getPrice() {
        if (daysInAdvance >= EARLY_BIRD_MIN_DAYS_IN_ADVANCE) {
            return EARLY_BIRD_PRICE;
        }
        return LATE_PRICE;
    }
}

class StudentAdvanceTicket extends AdvanceTicket {
    public StudentAdvanceTicket(Integer daysInAdvance) {
        super(daysInAdvance);
    }

    @Override
    public String toString() {
        return super.toString() + " (student)";
    }

    @Override
    public Integer getPrice() {
        return super.getPrice() / 2;
    }


}