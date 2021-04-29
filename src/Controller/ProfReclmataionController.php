<?php

namespace App\Controller;

use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProfReclmataionController extends AbstractController
{
    /**
     * @Route("/prof/reclmataion", name="prof_reclmataion")
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
      //  $profReclamation = $reclamationRepository->findBy(array( 'idUtilisateur'=> $user->getid() ));
        $profReclamation = $reclamationRepository->findBy(array( 'idutilisateur'=> '12345676' ));
        return $this->render('prof_reclmataion/index.html.twig', [
            'reclamations' => $profReclamation
        ]);
    }
}
