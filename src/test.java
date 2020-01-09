import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String time;
		
		time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
		System.out.println(time);
		
		System.out.println(LogTime.getCurrentTimeWtmp("root     :1           :1               Tue Jan  7 01:41 - crash  02:53"));


	}
}
