package springangular.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springangular.demo.OServiceInterface.AllUserDataInterface.UserInterface;
import springangular.demo.OServiceInterface.FriendsInterface.FriendsInterface;
import springangular.demo.OServiceInterface.PostingInterface.PostInterface;
import springangular.demo.XReposotiry.FriendsRepo;
import springangular.demo.ZModel.APostData;
import springangular.demo.ZModel.AnUser;
import springangular.demo.ZModel.Friends;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostAndFriendsController {

    @Autowired
    private PostInterface postInterface;

    @Autowired
    private FriendsInterface friendsInterface;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private FriendsRepo friendsRepo;

    @GetMapping(value = {"/getPosts"})
    public List<APostData> getAllPost(){
        return  postInterface.getAllPosts();
    }

    @GetMapping(value = {"/getPost/{postId}"})
    public APostData getPost(@PathVariable(value = "postId") Long id){
        return postInterface.getPost(id);
    }

    @PostMapping(value = {"{username}/savePost"})
    private  void savePost(@PathVariable("username") String username,@RequestBody APostData postData){
        postData.setUser(userInterface.getOneUser(username));
        postData.setPostedIn(new Date());
        postInterface.savePost(postData);
    }

    @RequestMapping(value = {"/delete/post/{id}"})
    public void deletePost(@PathVariable(value = "id") Long id){
         postInterface.deletePost(id);
    }

    /********************************************************************/

    @GetMapping(value = {"/{username}/getFriends"})
    public List<Friends> getAllFriends(@PathVariable("username") String username){

        return  friendsRepo.findByUser(userInterface.getOneUser(username));
    }
}
