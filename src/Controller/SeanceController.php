<?php

namespace App\Controller;

use App\Entity\Seance;
use App\Repository\SeanceRepository;

use App\Form\SeanceType;
use App\Entity\PropertySearch;
use App\Form\PropertySearchType;
use App\Entity\PriceSearch;
use App\Form\PriceSearchType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Twilio\Rest\Client;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mime\Email;

/**
 * @Route("/seance")
 */
class SeanceController extends Controller
{
    /**
     * @Route("/", name="seance_index", methods={"GET","POST"})
     */
    public function index(Request $request, SeanceRepository $seanceRepository)
    {

        $propertySearch = new PropertySearch();
        $form = $this->createForm(PropertySearchType::class,$propertySearch);
        $form->handleRequest($request);
        $seances= [];
        $sortType = 'ASC';
        $sortBy = $request->query->get('sortBy');
        $sortType = $request->query->get('sortType');
        $labelclasse = "Nom Classe";
        $labelmatiere = "Matiere";
        $labelheure = "Heure";
        $arrayclasse = [];
        $arraymatiere = [];
        $arrayheure = [];
        $defaultclasse = $request->query->get('classe');
        $defaultmatiere = $request->query->get('matiere');
        $defaultheure = $request->query->get('heure');
        if ($defaultclasse) {  $labelclasse = $defaultclasse;}
        if ($defaultmatiere) {  $labelmatiere = $defaultmatiere;}
        if ($defaultheure) {  $labelheure = $defaultheure;}
        $sorting = array();
        if ($sortBy && $sortType) {
            $sorting = array($sortBy => $sortType);
        }
        if ($sortType == 'ASC') {
            $sortType = 'DESC';
        }else{
            $sortType = 'ASC';
        }

        if($form->isSubmitted() && $form->isValid()) {
            //on récupère le nom d'article tapé dans le formulaire
            $nom = $propertySearch->getNom();
            if ($nom!="")
                //si on a fourni un nom d'article on affiche tous les articles ayant ce nom
                $seances= $seanceRepository->findByInput($nom);
            else
                //si si aucun nom n'est fourni on affiche tous les articles
                $seances= $this->getDoctrine()->getRepository(Seance::class)->findBy([], $sorting);
        }elseif ($defaultclasse || $defaultmatiere || $defaultheure) {
            $seances= $seanceRepository->findBySelection($defaultclasse, $defaultmatiere, $defaultheure);

        }else{
            $seances= $this->getDoctrine()->getRepository(Seance::class)->findBy([], $sorting);
        }

        $allseances= $this->getDoctrine()->getRepository(Seance::class)->findAll();
        foreach ($allseances as $allseance) {
            $arrayclasse[$allseance->getNomclasse()] = '';
            $arraymatiere[$allseance->getMatiere()] = '';
            $arrayheure[$allseance->getHeure()] = '';
        }

        $dropvalues['classe'] = array_keys($arrayclasse);
        $dropvalues['matiere'] = array_keys($arraymatiere);
        $dropvalues['heure'] = array_keys($arrayheure);


        $pagseance = $this->get('knp_paginator')->paginate(
        // Doctrine Query, not results
            $seances,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            4
        );
        return $this->render('seance/index.html.twig', [
            'form' =>$form->createView(),
            'seances' => $pagseance,
            'sortType' => $sortType,
            'defaultmatiere' => $defaultmatiere,
            'defaultheure' => $defaultheure,
            'defaultclasse' => $defaultclasse,
            'dropvalues' => $dropvalues,
            'labelclasse' => $labelclasse,
            'labelmatiere' => $labelmatiere,
            'labelheure' => $labelheure,
        ]);
    }

    /**
     * @Route("/new", name="seance_new", methods={"GET","POST"})
     */
    public function new(Request $request, FlashyNotifier $flashy): Response
    {
        $seance = new Seance();
        $form = $this->createForm(SeanceType::class, $seance);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($seance);
            $entityManager->flush();

            return $this->redirectToRoute('seance_index');
        }
        $flashy->success('Event created!', 'http://your-awesome-link.com');

        return $this->render('seance/new.html.twig', [
            'seance' => $seance,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idseance}", name="seance_show", methods={"GET"})
     */
    public function show(Seance $seance): Response
    {
        return $this->render('seance/show.html.twig', [
            'seance' => $seance,
        ]);
    }

    /**
     * @Route("/{idseance}/edit", name="seance_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Seance $seance, MailerInterface $mailer): Response
    {
        $form = $this->createForm(SeanceType::class, $seance);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            $email = (new Email())
                ->from('service.ecole147@gmail.com')
                ->to($seance->getIdens()->getEmail())
                ->subject('Changement !')
                ->html('
            <b>Nom Classe :</b><br><p> '. $seance->getNomclasse() .' </p>
            <b>Matiere :</b><br><p> '. $seance->getMatiere() .' </p>
            <b>Jour :</b><br><p> '. $seance->getDateseance() .' </p>
            <b>Heure :</b><br><p> '. $seance->getHeure() .' </p>
            ');
            $mailer->send($email);
            return $this->redirectToRoute('seance_index');
        }
        return $this->render('seance/edit.html.twig', [
            'seance' => $seance,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idseance}/", name="seance_delete", methods={"POST"})
     */
    public function delete(Request $request, Seance $seance): Response
    {
        if ($this->isCsrfTokenValid('delete'.$seance->getIdseance(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($seance);
            $entityManager->flush();
        }
        // Your Account SID and Auth Token from twilio.com/console
        $sid = 'AC3011c50f290ac621dc234181336d711f';
        $token = 'e87172dda1ef18dec89ab8d3ea406810';
        $client = new Client($sid, $token);

        // Use the client to do fun stuff like send text messages!
        $client->messages->create(
        // the number you'd like to send the message to
            '+21622372878',
            [
                // A Twilio phone number you purchased at twilio.com/console
                'from' => '+18327064871',
                // the body of the text message you'd like to send
                'body' => 'Bonjour Mr, votre seance le '.$seance->getDateseance().' heure'.$seance->getHeure().' classe'.$seance->getNomclasse().'est annulée'
            ]
        );
        return $this->redirectToRoute('seance_index');
    }



    /**
     * @Route("/art_cat/", name="article_par_cat")
     * Method({"GET", "POST"})
     */
    /* public function articlesParCategorie(Request $request) {
         $categorySearch = new CategorySearch();
         $form = $this->createForm(CategorySearchType::class,$categorySearch);
         $form->handleRequest($request);

         $articles= [];

         if($form->isSubmitted() && $form->isValid()) {
             $category = $categorySearch->getCategory();

             if ($category!="")
             {

                 $articles= $category->getArticles();
                 // ou bien
                 //$articles= $this->getDoctrine()->getRepository(Article::class)->findBy(['category' => $category] );
             }
             else
                 $articles= $this->getDoctrine()->getRepository(Article::class)->findAll();
         }

         return $this->render('articles/articlesParCategorie.html.twig',['form' => $form->createView(),'articles' => $articles]);
     }*/
    /**
     *@Route("/art_prix/",name="article_par_prix")
     *Method({"GET"})
     */
    public function articlesParPrix(Request$request){

        $priceSearch=new PriceSearch();
        $form=$this->createForm(PriceSearchType::class,$priceSearch);
        $form->handleRequest($request);
        $seances=[];
        if($form->isSubmitted()&&$form->isValid()){

            $minPrice=$priceSearch->getMinPrice();

            $maxPrice=$priceSearch->getMaxPrice();

            $articles=$this->getDoctrine()->getRepository(Seance::class)->findByPriceRange($minPrice,$maxPrice);}return$this->render('seance/articlesParPrix.html.twig',['form'=>$form->createView(),'articles'=>$articles]);
    }
}