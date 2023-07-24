package peaksoft.tasktracker.repository.jdbcTemplateService.jdbcTemplateImpl;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import peaksoft.tasktracker.config.JwtService;
import peaksoft.tasktracker.dto.response.BoardResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceInnerPageResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.repository.WorkSpaceRepository;
import peaksoft.tasktracker.repository.jdbcTemplateService.WorkSpaceJdbcTemplateService;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
@Getter
public class WorkSpaceJdbcTemplateServiceImpl implements WorkSpaceJdbcTemplateService {

    private final JwtService jwtService;
    private final JdbcTemplate jdbcTemplate;

    private String getAllWorkSpacesQuery() {
        String sql = "SELECT w.id AS id, w.name AS workSpaceName," +
                "  u.id AS userId," +
                "  CONCAT(u.first_name, ' ', u.last_name) AS fullName" +
                "  ,u.image as image" +
                "  FROM work_spaces AS w" +
                " JOIN users AS u ON w.admin_id = u.id" +
                "  WHERE u.id = ?";
        return sql;
    }

    @Override
    public List<WorkSpaceResponse> getAllWorkSpaces() {
        User user = jwtService.getAuthentication();
        List<WorkSpaceResponse> workSpaceResponses = jdbcTemplate.query(
                getAllWorkSpacesQuery(), new Object[]{user.getId()}, ((rs, rowNum) -> {
                    WorkSpaceResponse workSpaceResponse = new WorkSpaceResponse();
                    workSpaceResponse.setWorkSpaceId(rs.getLong("id"));
                    workSpaceResponse.setWorkSpaceName(rs.getString("workSpaceName"));
                    workSpaceResponse.setAdminId(rs.getLong("userId"));
                    workSpaceResponse.setAdminFullName(rs.getString("fullName"));
                    workSpaceResponse.setAdminImage(rs.getString("image"));
                    return workSpaceResponse;
                }
                ));
        return workSpaceResponses;
    }

    private String getWorkSpacesByIdQuery() {
        String sql = "SELECT w.id AS id, w.name AS workSpaceName," +
                "  u.id AS userId," +
                "  CONCAT(u.first_name, ' ', u.last_name) AS fullName" +
                "  ,u.image as image" +
                "  FROM work_spaces AS w" +
                " JOIN users AS u ON w.admin_id = u.id" +
                "  WHERE u.id = ? AND w.id=?";
        return sql;
    }

    @Override
    public WorkSpaceResponse getWorkSpaceById(Long workSpaceId) {
        User user = jwtService.getAuthentication();
        WorkSpaceResponse workSpaceResponse = jdbcTemplate.queryForObject(
                getWorkSpacesByIdQuery(), new Object[]{user.getId(), workSpaceId}, (rs, rowNum) -> {
                    WorkSpaceResponse workSpaceResponse1 = new WorkSpaceResponse();
                    workSpaceResponse1.setWorkSpaceId(rs.getLong("id"));
                    workSpaceResponse1.setWorkSpaceName(rs.getString("workSpaceName"));
                    workSpaceResponse1.setAdminId(rs.getLong("userId"));
                    workSpaceResponse1.setAdminFullName(rs.getString("fullName"));
                    workSpaceResponse1.setAdminImage(rs.getString("image"));
                    return workSpaceResponse1;
                });
        return workSpaceResponse;
    }

    @Override
    public WorkSpaceInnerPageResponse getInnerPageResponse(Long id) {
        User user = jwtService.getAuthentication();
        WorkSpaceInnerPageResponse workSpaceInnerPageResponse = jdbcTemplate.query(getAllWorkSpaceInnerPageQuery(), new Object[]{user.getId(), id}, (rs) -> {
            WorkSpaceResponse workSpaceResponse = new WorkSpaceResponse();
            List<BoardResponse> boardResponses = new ArrayList<>();
            while (rs.next()) {
                if (workSpaceResponse.getWorkSpaceId() == null) {
                    workSpaceResponse.setWorkSpaceId(rs.getLong("id"));
                    workSpaceResponse.setWorkSpaceName(rs.getString("workSpaceName"));
                    workSpaceResponse.setAdminId(rs.getLong("userId"));
                    workSpaceResponse.setAdminFullName(rs.getString("fullName"));
                    workSpaceResponse.setAdminImage(rs.getString("image"));
                }
                BoardResponse boardResponse = new BoardResponse();
                boardResponse.setBoardId(rs.getLong("boardId"));
                boardResponse.setBoardTitle(rs.getString("title"));
                boardResponse.setBoardBackGround(rs.getString("back_ground"));
                boardResponses.add(boardResponse);
            }
            WorkSpaceInnerPageResponse workSpaceInnerPageResponse1 = new WorkSpaceInnerPageResponse();
            workSpaceInnerPageResponse1.setWorkSpaceResponse(workSpaceResponse);
            workSpaceInnerPageResponse1.setBoardResponses(boardResponses);
            return workSpaceInnerPageResponse1;

        });
        return workSpaceInnerPageResponse;
    }


    private String getAllWorkSpaceInnerPageQuery() {
        String sql = "SELECT w.id AS id, w.name AS workSpaceName, " +
                "u.id AS userId, b.id AS boardId, b.title AS title, b.back_ground AS back_ground, " +
                "CONCAT(u.first_name, ' ', u.last_name) AS fullName, u.image AS image " +
                "FROM work_spaces AS w " +
                "JOIN users AS u ON w.admin_id = u.id " +
                "JOIN boards b ON w.id = b.work_space_id " +
                "WHERE u.id = ? AND w.id = ?";
        return sql;
    }


}

