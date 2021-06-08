package com.springbook.biz.board.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

import java.sql.*;
import java.util.List;

@Repository
public class BoardDAOSpring{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 쿼리문
	private final String BOARD_INSERT = "insert into board(seq, title, writer,content) "
			+ "values(?,?,?,?)";
//	private final String BOARD_INSERT = "insert into board(seq, title, writer,content) "
//			+ "values((select seq from (select IFNULL(max(SEQ), 0)+1 as seq from board) tmp),?,?,?)";

	private final String BOARD_UPDATE = "update board set " + "title=?,content=? where seq=?";

	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};	
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
	
	class BoardRowMapper implements RowMapper<BoardVO> {
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
			return board;
		}

	}
	
}
