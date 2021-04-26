<?php

namespace App\Controller;

use App\Repository\ClasseRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class StatesticsController extends AbstractController
{
    /**
     * @Route("/statestics", name="statestics")
     */
    public function index(ClasseRepository $classeRepository): Response
    {
        $classes = $classeRepository->findAll();
        $eleveLabel = [];
        $eleveData = [];

        foreach ($classes as $classe) {
            $eleveLabel[] = $classe->getNomclasse();
            $eleveData[] = $classe->getNbr();
        }

        return $this->render('statestics/index.html.twig', [
            'eleveLabel' => $eleveLabel,
            'eleveData' => $eleveData,
        ]);
    }
}
