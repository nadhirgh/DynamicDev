<?php

namespace App\Repository;

use App\Entity\PriceSearch;
use App\Entity\Seance;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Activites|null find($id, $lockMode = null, $lockVersion = null)
 * @method Activites|null findOneBy(array $criteria, array $orderBy = null)
 * @method Activites[]    findAll()
 * @method Activites[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class SeanceRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Seance::class);
    }

public function findByPriceRange($minValue,$maxValue){

        return$this->createQueryBuilder('a')
            ->andWhere('a.prix>=:minVal')
            ->setParameter('minVal',$minValue)
            ->andWhere('a.prix<=:maxVal')
            ->setParameter('maxVal',$maxValue)
            ->orderBy('a.id','ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult();

}

public function findByInput($nom){

    return$this->createQueryBuilder('a')
        ->andWhere('a.nomclasse LIKE :nom OR a.matiere LIKE :nom OR a.heure LIKE :nom')
        ->setParameter('nom', '%'.$nom.'%')
        ->getQuery()
        ->getResult();

}

}