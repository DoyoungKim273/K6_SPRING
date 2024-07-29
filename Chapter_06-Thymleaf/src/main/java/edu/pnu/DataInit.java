package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistance.BoardRepository;
import edu.pnu.persistance.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memberRepo.save(Member.builder().id("member1").password("abcd").name("둘리")
				.role("ROLE_USER").build());
		memberRepo.save(Member.builder().id("member2").password("abcd").name("도우너")
				.role("ROLE_ADMIN").build());
		
		for(long i = 1L; i <= 10L; i++) {
			boardRepo.save(Board.builder().title("title" + i).writer("member1")
					.content("content1" + i).build());
		}
		
		for(long i = 1L; i <= 10L; i++) {
			boardRepo.save(Board.builder().title("title" + i).writer("member2")
					.content("content2" + i).build());
		}
		
//		Random rd = new Random();
//		for(int i = 1; i < 5; i++) {
//			boardRepo.save(Board.builder()
//					.title("title1" + i)
//					.writer("member1")
//					.content("content1" + i)
//					.createDate(new Date())
//					.cnt(Math.abs(rd.nextLong()%100L))
//					.build()
//					);
//		}
//		
//		for(int i = 1; i < 5; i++) {
//			boardRepo.save(Board.builder()
//					.title("title2" + i)
//					.writer("member2")
//					.content("content2" + i)
//					.createDate(new Date())
//					.cnt(Math.abs(rd.nextLong()%100L))
//					.build()
//					);
//		}
	}
}
