<?php

namespace App\Controller;

use App\Entity\Classe;
use App\Form\ClasseType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/classe")
 */
class ClasseController extends Controller
{
    /**
     * @Route("/", name="classe_index", methods={"GET"})
     */
    public function index(Request $request)
    {
        $classes = $this->getDoctrine()
            ->getRepository(Classe::class)
            ->findAll();
        $allclasse = $this->get('knp_paginator')->paginate(
        // Doctrine Query, not results
            $classes,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            4
        );
        return $this->render('classe/index.html.twig', [
            'classes' => $allclasse,
        ]);
    }

    /**
     * @Route("/new", name="classe_new", methods={"GET","POST"})
     */
    public function new(Request $request, FlashyNotifier $flashy): Response
    {
        $classe = new Classe();
        $form = $this->createForm(ClasseType::class, $classe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($classe);
            $entityManager->flush();

            return $this->redirectToRoute('classe_index');
        }
        $flashy->success('Classe ajoutée', 'http://your-awesome-link.com');

        return $this->render('classe/new.html.twig', [
            'classe' => $classe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idclasse}", name="classe_show", methods={"GET"})
     */
    public function show(Classe $classe): Response
    {
        return $this->render('classe/show.html.twig', [
            'classe' => $classe,
        ]);
    }

    /**
     * @Route("/{idclasse}/edit", name="classe_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Classe $classe): Response
    {
        $form = $this->createForm(ClasseType::class, $classe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('classe_index');
        }

        return $this->render('classe/edit.html.twig', [
            'classe' => $classe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idclasse}", name="classe_delete", methods={"POST"})
     */
    public function delete(Request $request, Classe $classe): Response
    {
        if ($this->isCsrfTokenValid('delete'.$classe->getIdclasse(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($classe);
            $entityManager->flush();
        }

        return $this->redirectToRoute('classe_index');
    }
}
