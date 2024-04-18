package board;

import member.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataSource;
	
	public BoardDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String sql = 
			"""
			select
				a.no,
				b.name writer,
				a.title,
				a.create_date,
				a.hits
			from
				board a
				inner join member b on a.writer = b.id
			order by a.create_date desc
			""";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int no = resultSet.getInt("no");
				String writer = resultSet.getString("writer");
				String title = resultSet.getString("title");
				Date createDate = resultSet.getDate("create_date");
				int hits = resultSet.getInt("hits");
				list.add(new BoardVO(no, writer, title, createDate.toLocalDate(), hits));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public BoardVO getBoard(int searchNo) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		BoardVO vo = null;
		try {
			connection = dataSource.getConnection();
			String sql =
					"""
					select
						a.no,
						b.name writer,
						a.title,
						a.content,
						a.create_date,
						a.modify_date,
						a.hits
					from
						board a
						inner join member b on a.writer = b.id
					where
    					no = ?
					""";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, searchNo);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int no = resultSet.getInt("no");
				String writer = resultSet.getString("writer");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Date createDate = resultSet.getDate("create_date");
				Date modifyDate = resultSet.getDate("modify_date");
				int hits = resultSet.getInt("hits");
				vo = new BoardVO(no, writer, title, content, createDate.toLocalDate(), modifyDate == null ? null : modifyDate.toLocalDate(), hits);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	public int insertBoard(BoardVO vo) {
		int executeUpdate = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into board (writer, title, content) values (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, vo.getWriter());
			statement.setString(2, vo.getTitle());
			statement.setString(3, vo.getContent());
			
			executeUpdate = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
	public int updateBoard(BoardVO vo) {
		int executeUpdate = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update board set title = ?, content = ?, modify_date = sysdate where no = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getContent());
			statement.setInt(3, vo.getNo());

			executeUpdate = statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeUpdate;

	}
	public int deleteBoard(int deleteNo) {
		int executeUpdate = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "delete from board where no = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, deleteNo);

			executeUpdate = statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeUpdate;

	}
}
