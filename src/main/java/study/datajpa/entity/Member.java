package study.datajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
@NamedQuery(
	name = "Member.findByUserName",
	query = "select m from Member m where m.username = :username"
)
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	private String username;
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;


	public Member(String member) {
		this.username = member;
	}

	public Member(String username, int age, Team team) {
		this.username = username;
		this.age = age;
		if (team != null) {
			this.team = team;
		}
	}

	public Member(String username, int age) {
		this.username = username;
		this.age = age;
	}

	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}
}
