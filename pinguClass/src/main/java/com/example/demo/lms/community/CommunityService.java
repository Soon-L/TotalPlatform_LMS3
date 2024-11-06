package com.example.demo.lms.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	private final UserRepository userRepository;

	public List<Community> getList() {
		// TODO Auto-generated method stub
		return communityRepository.findAll();
	}

	public Community getdetail(Integer id) throws UserException {
		Optional<Community> community = this.communityRepository.findById(id);
		if(community.isPresent()) {
			return community.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
	}
	
	//로그인시 커뮤니티 글 조회
		public Page<Community> getList(int page, String id)  throws UserException {
			List<Sort.Order> sorts = new ArrayList<>();
			sorts.add(Sort.Order.desc("questionDate"));

			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
			
			return this.communityRepository.findByUser(getByUser(id), pageable);
		}
		
		//유저 정보 가져오기
		public User getByUser(String id) throws UserException {
			Optional<User> user = this.userRepository.findById(id);
			
			if(user.isPresent()) { 
				return user.get();
			} else {
				throw new UserException("유저 정보가 없습니다.");
			}
		}
		
		
		
		
		
		
	
	/* 마이페이지에서 특정 사용자의 삭제되지 않은 커뮤니티 글 목록을 페이징처리 - 남동현 */
	public Page<Community> getMyPageCommunityList(int page, String username)
			throws UserException {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("lastUpdate")));

        // 사용자 검증
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UserException("User not found"));

        // 해당 사용자가 작성한 삭제되지 않은 커뮤니티 글 반환
        return communityRepository.findByUserAndDeleteYn(user, "N", pageable);
    }
	
	/* 유저ID로 커뮤니티 글의 총 갯수 조회 */
	public int getCommunityCountById(String userId) {
		
		User user = this.userRepository.findUserById(userId);
		
		return this.communityRepository.countUserById(user.getUserId());
	}

	public List<Community> getUserByKeyword(String userId, int startNo, int pageSize) {
		
		User user = this.userRepository.findUserById(userId);
		
		return this.communityRepository.findAllById(user.getUserId(), startNo, pageSize);
	}

	public void updateCommunity(Integer cmId, String title, String content)
	throws UserException {
		
		 	Community community = getdetail(cmId);
		    community.setTitle(title);
		    community.setContent(content);
		    community.setLastUpdate(LocalDateTime.now());
		    communityRepository.save(community);
		
		
	}


		

	
		
		
		
		
		}