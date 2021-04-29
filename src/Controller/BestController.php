<?php

namespace App\Controller;

use App\Repository\ClasseRepository;
use App\Repository\SeanceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Symfony\Component\HttpFoundation\Request;

class BestController extends AbstractController
{
    /**
     * @Route("/best", name="best")
     */
    public function index(SeanceRepository $seanceRepository, Request $request): Response
    {
        $print = $request->query->get('print');
        $class_name = "3A1";
        $seances = $seanceRepository->findBy(['nomclasse' => $class_name]);
        $days = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"];
        $horaires = ["08:00-10:00", "10:00-12:00", "PAUSE", "14:00-16:00", "16:00-18:00"];
        $planing = []; // planing initialisation
        foreach ($days as $day) {
            foreach ($horaires as $horaire) {
                $planing[$day][$horaire] = "";
            }
        }
        foreach ($seances as $seance) {
            $planing[$seance->getDateseance()][$seance->getHeure()] = $seance->getMatiere();
        }

        if ($print) {
            $render_pdf =  $this->renderView('best/pdf.html.twig', [
                'planing' => $planing,
            ]);
            $dompdf = new Dompdf();
            $dompdf->loadHtml($render_pdf);
            $dompdf->setPaper('A4', 'landscape');
            $dompdf->render();
            $dompdf->stream("PDF.pdf", array("Attachment" => false));
            die();
        }


        return $this->render('best/index.html.twig', [
            'planing' => $planing,
        ]);
    }


    /**
     * @Route("/bestprof", name="bestprof")
     */
    public function bestprof(SeanceRepository $seanceRepository, Request $request): Response
    {
        $print = $request->query->get('print');
        $prof = "13";
        $seances = $seanceRepository->findBy(['idens' => $prof]);
        $days = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"];
        $horaires = ["08:00-10:00", "10:00-12:00", "PAUSE", "14:00-16:00", "16:00-18:00"];
        $planing = []; // planing initialisation
        foreach ($days as $day) {
            foreach ($horaires as $horaire) {
                $planing[$day][$horaire] = "";
            }
        }
        foreach ($seances as $seance) {
            $planing[$seance->getDateseance()][$seance->getHeure()] = $seance->getNomclasse();
        }

        if ($print) {
            $render_pdf =  $this->renderView('best/pdf.html.twig', [
                'planing' => $planing,
            ]);
            $dompdf = new Dompdf();
            $dompdf->loadHtml($render_pdf);
            $dompdf->setPaper('A4', 'landscape');
            $dompdf->render();
            $dompdf->stream("PDF.pdf", array("Attachment" => false));
            die();
        }


        return $this->render('best/bestprof.html.twig', [
            'planing' => $planing,
        ]);
    }

}
