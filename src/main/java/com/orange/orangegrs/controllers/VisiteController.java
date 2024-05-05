package com.orange.orangegrs.controllers;

import com.orange.orangegrs.entities.Site;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.services.SiteService;
import com.orange.orangegrs.services.UserService;
import com.orange.orangegrs.services.VisiteService;
import com.orange.orangegrs.utils.JWTUtils;
import com.orange.orangegrs.utils.UploadFileUtils;
import com.orange.orangegrs.utils.VisiteRequest;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/visites")
public class VisiteController {


    @Autowired
    private VisiteService visiteService;


    @Autowired
    private SiteService siteService;


    @Autowired
    private UserService userService;


    @Autowired
    private JWTUtils jwtUtils;



    @Autowired
    private UploadFileUtils uploadFileUtils;




    @GetMapping("/")
    public ResponseEntity getAllVisites(HttpServletRequest request){
        try{
            //extract token from request
            String authHeader = request.getHeader("Authorization");
            String authToken = authHeader.substring(7);
            User user = this.userService.findUserByLogin(jwtUtils.extractUserNameFromToken(authToken));
            if(user.getProfile().getProfile().equals("Technicien")){
                return ResponseEntity.ok(this.visiteService.getAllVisiteByUserLogin(user.getLogin()));
            }else{
                return ResponseEntity.ok(this.visiteService.getAllVisite());
            }

        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }


    @GetMapping("/{siteCode}")
    public ResponseEntity getAllVisitesHaveSiteCodeEqual(HttpServletRequest request,@PathVariable String siteCode){
        try{
            //extract token from request
            String authHeader = request.getHeader("Authorization");
            String authToken = authHeader.substring(7);
            User user = this.userService.findUserByLogin(jwtUtils.extractUserNameFromToken(authToken));
            if(user.getProfile().getProfile().equals("Technicien")){
                return ResponseEntity.ok(this.visiteService.getAllVisiteByUserLoginAndSiteCode(user.getLogin(),siteCode));
            }else{
                return ResponseEntity.ok(this.visiteService.findVisiteByCodeSite(siteCode));
            }

        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request / "+e.getMessage());
        }
    }




    @PostMapping(name = "/")
    public ResponseEntity addNewVisite(HttpServletRequest request, @ModelAttribute VisiteRequest visiteRequest,@RequestParam("photo") MultipartFile photo){
        System.out.println("add new visite method controller");
        try{
            //extract token from request
            String authHeader = request.getHeader("Authorization");
            String jwtToken = authHeader.substring(7);
            //créé une instance visite
            Visite visite = getInstanceVisite(jwtToken,visiteRequest, photo, false);
            //persister la visite dans la base de donnée
            return ResponseEntity.ok(this.visiteService.addNewVisite(visite));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (MaxUploadSizeExceededException maxup){
            throw new RuntimeException("Impossible d'ajouter l'image size = "+maxup.getMaxUploadSize());
        }catch (Exception e){
            System.out.println("exception add visite = "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }




    @PutMapping("/")
    public ResponseEntity updateVisite(@ModelAttribute VisiteRequest visiteRequest,@RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
        if(photo == null){
            Visite visite = getInstanceVisiteForUpdateSansImage(visiteRequest);
            Visite NewVisite = this.visiteService.updateVisite(visite);
            return ResponseEntity.ok(NewVisite);
        }else{
            try{
                Visite visite = getInstanceVisite("", visiteRequest, photo, true);
                Visite NewVisite = this.visiteService.updateVisite(visite);
                return ResponseEntity.ok(NewVisite);
            }catch (MaxUploadSizeExceededException maxup){
                throw new RuntimeException("Impossible d'ajouter l'image size = "+maxup.getMaxUploadSize());
            }catch (Exception e){
                System.out.println("exception add visite = "+ e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
            }
        }

    }



    @DeleteMapping("/admin/{visiteId}")
    public ResponseEntity deleteVisite(@PathVariable int visiteId){
        if(this.visiteService.deleteVisiteByVisiteId(visiteId) == 1){
            return ResponseEntity.ok("Visite supprimé avec succées");
        }else{
            return ResponseEntity.status(400).body("Probléme lors de suppression");
        }
    }







    Visite getInstanceVisite(String authToken, VisiteRequest visiteRequest, MultipartFile photo, boolean isUpdate) throws IOException{
        Visite visite = new Visite();
        Site site = this.siteService.findSiteBySiteId(visiteRequest.getSiteId());
        User user;
        if(isUpdate){
            Visite oldVisite = this.visiteService.findOne(visiteRequest.getVisiteId());
            user = oldVisite.getLogin();
            visite.setDateInsertion(oldVisite.getDateInsertion());
            visite.setVisiteId(visiteRequest.getVisiteId());
        }else{
            user = this.userService.findUserByLogin(jwtUtils.extractUserNameFromToken(authToken)) ;
        }
        visite.setSite(site);
        visite.setLogin(user);
        visite.setCommentaire(visiteRequest.getCommentaire());
        visite.setIndexCompteur(visiteRequest.getIndexCompteur());
        visite.setOo(visiteRequest.getOo());
        visite.setTt(visiteRequest.getTt());
        visite.setOtn(visiteRequest.getOtn());
        visite.setTag(visiteRequest.getTag());
        try {
            visite.setPhotoCompteur(uploadFileUtils.uploadImageToFileSystem(photo));
        }catch (IOException e) {
            throw new IOException("Impossible de stocker le fichier ", e);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de transférer le fichier ", e);
        } catch (MaxUploadSizeExceededException maxup) {
            throw new MaxUploadSizeExceededException(maxup.getMaxUploadSize());
        }
        return visite;
    }



    Visite getInstanceVisiteForUpdateSansImage(VisiteRequest visiteRequest){
        Visite visite = new Visite();
        Site site = this.siteService.findSiteBySiteId(visiteRequest.getSiteId());
        Visite oldVisite = this.visiteService.findOne(visiteRequest.getVisiteId());
        visite.setVisiteId(visiteRequest.getVisiteId());
        visite.setSite(site);
        visite.setLogin(oldVisite.getLogin());
        visite.setCommentaire(visiteRequest.getCommentaire());
        visite.setIndexCompteur(visiteRequest.getIndexCompteur());
        visite.setOo(visiteRequest.getOo());
        visite.setTt(visiteRequest.getTt());
        visite.setOtn(visiteRequest.getOtn());
        visite.setPhotoCompteur(oldVisite.getPhotoCompteur());
        visite.setDateInsertion(oldVisite.getDateInsertion());
        visite.setTag(oldVisite.getTag());
        return visite;
    }
}
