package online.mrsys.movierecommender.action.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import online.mrsys.movierecommender.action.base.BaseAction;
import online.mrsys.movierecommender.action.base.WebConstant;
import online.mrsys.movierecommender.domain.Movie;
import online.mrsys.movierecommender.domain.Rating;

public class DeleteMovieAction extends BaseAction {
    
    private static final long serialVersionUID = -863968185438174742L;
    
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        Movie movie = movieManager.getMovieById(getId());
        if (movie == null) {
            actionContext.getSession().put(WebConstant.INTERCEPT_2, "Movie does not exist");
            return ERROR;
        }
        List<Rating> ratings = movieManager.getRatingsByMovie(movie);
        for (Rating rating : ratings) {
            movieManager.deleteRating(rating.getId());
        }
        movieManager.deleteMovie(getId());
        actionContext.getSession().put(WebConstant.INTERCEPT_2, "Success");
        return SUCCESS;
    }
    
}
